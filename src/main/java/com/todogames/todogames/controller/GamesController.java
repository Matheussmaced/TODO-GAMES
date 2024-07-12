package com.todogames.todogames.controller;

import java.util.UUID;
import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todogames.todogames.DTO.CreateGameDto;
import com.todogames.todogames.DTO.UpdateGameDto;
import com.todogames.todogames.entity.Games;
import com.todogames.todogames.service.GameService;

@Controller
@RequestMapping("/users")
public class GamesController {
  private final GameService gameService;

  public GamesController(GameService gameService) {
    this.gameService = gameService;
  }

  @PostMapping("/{userId}/games")
  public ResponseEntity<Games> createGames(@PathVariable("userId") String userId,
      @RequestBody CreateGameDto createGameDto) {

    var gamesCreate = gameService.createGames(userId, createGameDto);

    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
        .buildAndExpand(gamesCreate.getId()).toUri();

    return ResponseEntity.created(location).body(gamesCreate);
  }

  @PutMapping("/games/{id}")
  public ResponseEntity<Void> updateGameById(@PathVariable("id") String id, @RequestBody UpdateGameDto updateGameDto) {
    gameService.updateGames(id, updateGameDto);

    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("/games/{id}")
  public ResponseEntity<Void> deleteGameById(@PathVariable("id") String id) {
    gameService.deleteGame(id);

    return ResponseEntity.noContent().build();
  }
}
