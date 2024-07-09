package com.todogames.todogames.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

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
  public User findById(UUID id) {
    return userRepository.findById(id).orElseThrow();
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
  public void delete(UUID id) {
    User userToDelete = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    userRepository.delete(userToDelete);
  }

}
