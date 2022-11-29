package controller;

import model.entity.Page;

public class ServletConfig {
  public static Page[] pages = {
    new Page("teams", "Equipos"),
    new Page("players", "Jugadores"),
    new Page("matches", "Partidos"),
    new Page("squads", "Plantillas"),
  };

  public static enum sessionVars {
    CONNECTION,

    CURRENT_SEASON,
  };

  public static enum requestVars {
    TEAMS,
    PLAYERS,
  }
}
