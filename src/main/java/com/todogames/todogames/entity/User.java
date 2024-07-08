package com.todogames.todogames.entity;

import java.util.UUID;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID userId;

  @Column(name = "userName")
  private String name;

  @Column(name = "password")
  private String password;

  @CreationTimestamp
  private Instant creationTimestamp;

  @UpdateTimestamp
  private Instant updateTimeInstant;

  @OneToOne(cascade = CascadeType.ALL)
  private Games games;

  public User() {
  }

  public User(UUID userId, String name, String password, Instant creationTimestamp, Instant updateTimeInstant,
      Games games) {
    this.userId = userId;
    this.name = name;
    this.password = password;
    this.creationTimestamp = creationTimestamp;
    this.updateTimeInstant = updateTimeInstant;
    this.games = games;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getName() {
    return name;
  }

  public String getPassword() {
    return password;
  }

  public Instant getCreationTimestamp() {
    return creationTimestamp;
  }

  public Instant getUpdateTimeInstant() {
    return updateTimeInstant;
  }

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setCreationTimestamp(Instant creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }

  public void setUpdateTimeInstant(Instant updateTimeInstant) {
    this.updateTimeInstant = updateTimeInstant;
  }

  public Games getGames() {
    return games;
  }

  public void setGames(Games games) {
    this.games = games;
  }

}
