package com.todogames.todogames.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.todogames.todogames.DTO.UpdateGameDto;
import com.todogames.todogames.service.GameService;

@Controller
@RequestMapping("/users/games")
public class GamesController {
  private final GameService gameService;

  public GamesController(GameService gameService) {
    this.gameService = gameService;
  }

  @PutMapping("{id}")
  public ResponseEntity<Void> updateGameById(@PathVariable("id") String id, @RequestBody UpdateGameDto updateGameDto) {
    gameService.updateGames(id, updateGameDto);

    return ResponseEntity.noContent().build();
  }
}
