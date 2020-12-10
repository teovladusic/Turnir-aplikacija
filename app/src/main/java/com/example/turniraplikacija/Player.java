package com.example.turniraplikacija;

import java.util.Date;

public class Player {

    private String team_name;
    private String name;
    private String last_name;
    private String date;
    private String number;
    private Integer goals = 0;
    private Integer yellow_cards = 0;
    private Integer red_cards = 0;
    private Integer number_of_games = 0;


    public Player(String team_name, String name, String last_name, String date, String number) {
        this.team_name = team_name;
        this.name = name;
        this.last_name = last_name;
        this.date = date;
        this.number = number;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getYellow_cards() {
        return yellow_cards;
    }

    public void setYellow_cards(Integer yellow_cards) {
        this.yellow_cards = yellow_cards;
    }

    public Integer getRed_cards() {
        return red_cards;
    }

    public void setRed_cards(Integer red_cards) {
        this.red_cards = red_cards;
    }

    public Integer getNumber_of_games() {
        return number_of_games;
    }

    public void setNumber_of_games(Integer number_of_games) {
        this.number_of_games = number_of_games;
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
