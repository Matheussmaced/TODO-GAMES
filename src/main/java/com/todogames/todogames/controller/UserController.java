package com.todogames.todogames.controller;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todogames.todogames.entity.User;
import com.todogames.todogames.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<User>> findAll() {
    var users = userService.findAll();

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable UUID id) {
    var user = userService.findById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<User> create(@RequestBody User userToCreate) {
    var userCreated = userService.create(userToCreate);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(userCreated.getUserId()).toUri();

    return ResponseEntity.created(location).body(userCreated);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteById(@PathVariable UUID id) {
    userService.delete(id);

    return ResponseEntity.noContent().build();
  }
}
