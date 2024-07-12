package com.todogames.todogames.service;

import org.springframework.stereotype.Service;

import com.todogames.todogames.DTO.CreateGameDto;
import com.todogames.todogames.DTO.UpdateGameDto;
import com.todogames.todogames.entity.Games;

@Service
public interface GameService {
  Games updateGames(String id, UpdateGameDto updateGameDto);

  Games createGames(String id, CreateGameDto createGameDto);
}
