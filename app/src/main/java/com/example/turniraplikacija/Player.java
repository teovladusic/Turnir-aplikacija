package com.example.turniraplikacija;

import java.util.Date;

public class Player {

    private String team_name;
    private String name;
    private String last_name;
    private String day;
    private String month;
    private String year;
    private String number;
    private Integer goals;
    private Integer yellow_cards;
    private Integer red_cards;
    private Integer number_of_games;
    private boolean isBestPlayer;
    private boolean isTopScorer;

    public Player(String team_name, String name, String last_name, String day, String month, String year, String number) {
        this.team_name = team_name;
        this.name = name;
        this.last_name = last_name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public Integer getGoals() {
        return goals;
    }

    public void setGoals(Integer goals) {
        this.goals = goals;
    }

    public void addGoals(Integer goals){
        this.goals = this.goals + goals;
    }
}
