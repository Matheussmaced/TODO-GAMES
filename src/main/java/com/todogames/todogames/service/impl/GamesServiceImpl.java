package com.todogames.todogames.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.todogames.todogames.DTO.UpdateGameDto;
import com.todogames.todogames.entity.Games;
import com.todogames.todogames.repository.GamesRepository;
import com.todogames.todogames.service.GameService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class GamesServiceImpl implements GameService {
  private final GamesRepository gamesRepository;

  public GamesServiceImpl(GamesRepository gamesRepository) {
    this.gamesRepository = gamesRepository;
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
}
