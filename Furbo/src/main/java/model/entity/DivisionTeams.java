package model.entity;

import java.util.ArrayList;

public class DivisionTeams {
  public int division;
  public ArrayList<Team> teams;

  public DivisionTeams(int division, ArrayList<Team> teams) {
    this.division = division;
    this.teams = teams;
  }
}
