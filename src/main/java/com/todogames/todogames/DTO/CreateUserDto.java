package com.todogames.todogames.DTO;

import java.util.List;

public record CreateUserDto(String name, String password, List<CreateGameDto> games) {
}
