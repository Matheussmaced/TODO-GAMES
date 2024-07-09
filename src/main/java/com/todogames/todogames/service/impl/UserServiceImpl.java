package com.todogames.todogames.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.todogames.todogames.DTO.UpdateUserDto;
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
  public User create(User userToCreate) {
    return userRepository.save(userToCreate);
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
