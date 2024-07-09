package com.todogames.todogames.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.todogames.todogames.DTO.UpdateUserDto;
import com.todogames.todogames.entity.User;

@Service
public interface UserService {
  User findById(String id);

  List<User> findAll();

  User create(User userToCreate);

  void delete(String id);

  User updateUser(String id, UpdateUserDto updateUserDto);
}
