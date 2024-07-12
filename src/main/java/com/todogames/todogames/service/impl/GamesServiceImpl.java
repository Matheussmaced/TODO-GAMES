package com.todogames.todogames.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.todogames.todogames.DTO.CreateGameDto;
import com.todogames.todogames.DTO.UpdateGameDto;
import com.todogames.todogames.entity.Games;
import com.todogames.todogames.entity.User;
import com.todogames.todogames.repository.GamesRepository;
import com.todogames.todogames.repository.UserRepository;
import com.todogames.todogames.service.GameService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GamesServiceImpl implements GameService {
  private final GamesRepository gamesRepository;
  private final UserRepository userRepository;

  public GamesServiceImpl(GamesRepository gamesRepository, UserRepository userRepository) {
    this.gamesRepository = gamesRepository;
    this.userRepository = userRepository;
  }

  public Games createGames(String userId, CreateGameDto createGameDto) {
    var userUuid = UUID.fromString(userId);
    User user = userRepository.findById(userUuid).orElseThrow(() -> new EntityNotFoundException("User not found"));

    Games games = new Games();
    games.setName(createGameDto.name());
    games.setDescription(createGameDto.description());
    games.setCompleted(createGameDto.completed());
    games.setUser(user);

    return gamesRepository.save(games);
  }

  @Override
  public Games updateGames(String id, UpdateGameDto updateGameDto) {
    var gameId = UUID.fromString(id);
    var gamesEntity = gamesRepository.findById(gameId)
        .orElseThrow(() -> new EntityNotFoundException("Game not found"));

    if (updateGameDto.name() != null) {
      gamesEntity.setName(updateGameDto.name());
    }
    if (updateGameDto.description() != null) {
      gamesEntity.setDescription(updateGameDto.description());
    }
    if (updateGameDto.completed() == false || updateGameDto.completed() == true) {
      gamesEntity.setCompleted(updateGameDto.completed());
    }
    return gamesRepository.save(gamesEntity);
  }

  @Override
  public void deleteGame(String id) {
    var gameId = UUID.fromString(id);

    Games gameToDelete = gamesRepository.findById(gameId)
        .orElseThrow(() -> new EntityNotFoundException("Game not found"));

    gamesRepository.delete(gameToDelete);
  }

}
