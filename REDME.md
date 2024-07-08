# Projeto Todo Games

## Objetivo do projeto:

O objetivo do projeto é criar uma lista de jogos relacionada ao usuario, onde você pode adicionar o jogo que você deseja finalizar, e quando finalizado você poderá marcar como finalizado!

## Diagrama de Classes:

```mermaid
classDiagram
    class User {
        id: int
        name: string
        password: string
    }
    class Game {
        id: int
        name: string
        completed: bool
        description: string
    }

    User --> Game : has

```