package com.todogames.todogames.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todogames.todogames.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
