package model.entity;

import java.util.ArrayList;

public class PositionPlayers {
  public Position position;
  public ArrayList<Player> players;

  public PositionPlayers(Position position, ArrayList<Player> players) {
    this.position = position;
    this.players = players;
  }
}
