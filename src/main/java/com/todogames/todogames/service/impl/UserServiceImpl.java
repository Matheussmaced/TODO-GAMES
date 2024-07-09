package com.todogames.todogames.service.impl;

import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.todogames.todogames.DTO.CreateUserDto;
import com.todogames.todogames.DTO.UpdateUserDto;
import com.todogames.todogames.entity.Games;
import com.todogames.todogames.entity.User;
import com.todogames.todogames.repository.UserRepository;
import com.todogames.todogames.service.UserService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findById(String id) {
    var UserId = UUID.fromString(id);

    return userRepository.findById(UserId).orElseThrow();
  }

  @Override
  public List<User> findAll() {
    List<User> users = userRepository.findAll();
    if (users != null) {
      return users;
    } else {
      return List.of();
    }
  }

  @Override
  public User create(CreateUserDto createUserDto) {
    User user = new User();
    user.setName(createUserDto.name());
    user.setPassword(createUserDto.password());

    List<Games> games = createUserDto.games().stream().map(createGameDto -> {
      Games game = new Games();
      game.setName(createGameDto.name());
      game.setDescription(createGameDto.description());
      game.setCompleted(createGameDto.completed());
      game.setUser(user);
      return game;
    }).collect(Collectors.toList());

    user.setGames(games);
    return userRepository.save(user);
  }

  @Override
  public void delete(String id) {
    var userId = UUID.fromString(id);

    User userToDelete = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));
    userRepository.delete(userToDelete);
  }

  @Override
  public User updateUser(String id, UpdateUserDto updateUserDto) {
    var userId = UUID.fromString(id);
    var userEntity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

    if (updateUserDto.name() != null) {
      userEntity.setName(updateUserDto.name());
    }
    if (updateUserDto.password() != null) {
      userEntity.setPassword(updateUserDto.password());
    }
    return userRepository.save(userEntity);
  }
}
