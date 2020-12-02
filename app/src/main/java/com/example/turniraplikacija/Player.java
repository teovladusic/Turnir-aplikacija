package com.example.turniraplikacija;

import java.util.Date;

public class Player {

    private String team_name;
    private String name;
    private String last_name;
    private Date birth_date;
    private String number;
    private Integer goals;
    private Integer yellow_cards;
    private Integer red_cards;
    private Integer number_of_games;
    private boolean isBestPlayer;
    private boolean isTopScorer;

    public Player(String team_name, String name, String last_name, Date birth_date, String number) {
        this.team_name = team_name;
        this.name = name;
        this.last_name = last_name;
        this.birth_date = birth_date;
        this.number = number;
    }
}
