package com.gngg.gamewebsite.models;

public class PlayoffMatchup {
    private String team1;
    private String team2;
    private Integer team1Wins;
    private Integer team2Wins;
    private Integer winsNeeded;

    public PlayoffMatchup(String team1, String team2, Integer team1Wins, Integer team2Wins, Integer winsNeeded) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Wins = team1Wins;
        this.team2Wins = team2Wins;
        this.winsNeeded = winsNeeded;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public Integer getTeam1Wins() {
        return team1Wins;
    }

    public Integer getTeam2Wins() {
        return team2Wins;
    }

    public void setTeam1Wins(Integer team1Wins) {
        this.team1Wins = team1Wins;
    }

    public void setTeam2Wins(Integer team2Wins) {
        this.team2Wins = team2Wins;
    }

    private String isSeriesFinished() {
        if(team1Wins >= winsNeeded) return team1;
        else if(team2Wins >= winsNeeded) return team2;
        else return null;
    }
}
