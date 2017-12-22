package com.gngg.gamewebsite.models;

public class PlayoffMatchup {
    private Team team1;
    private Team team2;
    private Integer winsNeeded;
    private Integer round;
    private Integer matchupID;
    private Integer team1FromId;
    private Integer team2FromId;

    public PlayoffMatchup(Team team1, Team team2, Integer winsNeeded, Integer round, Integer matchupID) {
        this.team1 = team1;
        this.team2 = team2;
        this.winsNeeded = winsNeeded;
        this.round = round;
        this.matchupID = matchupID;
        team1FromId = 0;
        team2FromId = 0;
        setHeadstart();
    }

    public PlayoffMatchup(Integer winsNeeded, Integer round, Integer matchupID, Integer team1FromId, Integer team2FromId) {
        this.winsNeeded = winsNeeded;
        this.round = round;
        this.matchupID = matchupID;
        this.team1FromId = team1FromId;
        this.team2FromId = team2FromId;
    }

    public Team getTeam1() {
        return team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
        setHeadstart();
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
        setHeadstart();
    }

    public Integer getTeam1Wins() {
        return team1.playoffWins;
    }

    public Integer getTeam2Wins() {
        return team2.playoffWins;
    }

    public Integer getWinsNeeded() {
        return winsNeeded;
    }

    public Integer getRound() {
        return round;
    }

    public Integer getMatchupID() {
        return matchupID;
    }

    public Integer getTeam1FromId() {
        return team1FromId;
    }

    public Integer getTeam2FromId() {
        return team2FromId;
    }

    public Team isSeriesFinished() {
        if(team1.playoffWins >= winsNeeded) return team1;
        else if(team2.playoffWins >= winsNeeded) return team2;
        else return null;
    }

    private void setHeadstart(){
        if(team1 == null || team2 == null) return;
        if(team1.wins > team2.wins) {
            team1.playoffWins = team1.wins - team2.wins;
            if(team1.playoffWins > (winsNeeded - 1)) team1.playoffWins = winsNeeded - 1;
        }
        else if(team2.wins > team1.wins) {
            team2.playoffWins = team2.wins - team1.wins;
            if(team2.playoffWins > (winsNeeded - 1)) team2.playoffWins = winsNeeded - 1;
        }
    }
}
