package com.example.turniraplikacija;

import java.io.Serializable;

public class GameEvent implements Serializable {
    private long scorerID;
    private String event;   //goal, card..
    private String team;

    public GameEvent() {
    }

    public GameEvent(long scorerID, String event, String team) {
        this.scorerID = scorerID;
        this.event = event;
        this.team = team;
    }

    public long getScorerID() {
        return scorerID;
    }

    public void setScorerID(long scorerID) {
        this.scorerID = scorerID;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}