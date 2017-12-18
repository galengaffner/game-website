package com.gngg.gamewebsite.models;


public class Team {
    String name;
    Integer losses = 0;
    Integer wins = 0;
    Integer playoffWins = 0;
    Integer playoffSeed = 0;
    boolean isEliminated = false;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getLosses() {
        return losses;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getPlayoffWins() { return playoffWins; }

    public Integer getPlayoffSeed() {
        return playoffSeed;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLosses(Integer losses) {
        this.losses = losses;
    }

    public void setWins(Integer wins) {
        this.wins = wins;
    }

    public void setPlayoffWins(Integer playoffWins) { this.playoffWins = playoffWins; }

    public void setPlayoffSeed(Integer playoffSeed) {
        this.playoffSeed = playoffSeed;
    }

    public void setEliminated(boolean eliminated) {
        isEliminated = eliminated;
    }

    public void incrementWinOrLoss(boolean didWin){
       if(didWin) wins++;
       else losses++;
    }

    public void incrementPlayoffWinOrLoss(boolean didWin){
        if(didWin) playoffWins++;
    }
}
