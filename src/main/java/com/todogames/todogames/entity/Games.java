package com.todogames.todogames.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_games")
public class Games {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "gameName")
  private String name;

  @Column(name = "description")
  private String description;

  @Column(name = "completed")
  private boolean completed;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Games() {
  }

  public Games(UUID id, String name, String description, boolean completed, User user) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.completed = completed;
    this.user = user;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public User getUser() {
    return user;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
