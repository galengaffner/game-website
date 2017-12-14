package com.gngg.gamewebsite.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class FirstGame{
    @NotNull(message = "Must enter a number")
    @Min(value = 1, message = "Must have a value greater than 0")
    private Integer numRegularGames;
    @NotNull(message = "Must enter a number")
    @Min(value = 1, message = "Must have a value greater than 0")
    private Integer numPlayoffGames;
    @NotNull(message = "Must enter a number")
    @Min(value = 2, message = "Must have a value greater than 2")
    @Max(value = 4, message = "No more than 4 teams allowed")
    private Integer numTeams;
    private Team[] teams;
    private Categories category = Categories.CreativeCat;

    public FirstGame() {
        this(2, 10, 4);
    }

    public FirstGame(int numTeams, int numRegularGames, int numPlayoffGames) {
        this.numTeams = numTeams;
        this.numRegularGames = numRegularGames;
        this.numPlayoffGames = numPlayoffGames;
        teams = new Team[this.numTeams];
        for(int i = 0; i < this.numTeams; i++){
            teams[i] = (new Team(String.format("Team %s", i + 1)));
        }
    }

    public void setNumRegularGames(Integer numRegularGames) {
        this.numRegularGames = numRegularGames;
    }

    public void setNumPlayoffGames(Integer numPlayoffGames) {
        this.numPlayoffGames = numPlayoffGames;
    }

    public void setNumTeams(Integer numTeams) {
        teams = new Team[this.numTeams];
        for(int i = 0; i < this.numTeams; i++){
            teams[i] = (new Team(String.format("Team %s", i + 1)));
        }
        this.numTeams = numTeams;
    }

    public Integer getNumRegularGames() {
        return numRegularGames;
    }

    public Integer getNumPlayoffGames() {
        return numPlayoffGames;
    }

    public Integer getNumTeams() {
        return numTeams;
    }

    public List<String> getTeamsNames() {
        List<String> list = new ArrayList<>();
        for(Team t : teams)
            list.add(t.getName());
        return list;
    }

    public String getRandomCategory(){
        category = category.getRandomName();
        return  category.getName();
    }

    public void gameResult(String teamName, boolean didWin){
        for(Team t : teams){
            if(t.getName().equals(teamName)){
                t.incrementWinOrLoss(didWin);
                return;
            }
        }
    }

    public void gameResult(String teamName, boolean didWin, String vsName){
        gameResult(teamName, didWin);
        gameResult(vsName, !didWin);
    }

    public void resetWinLossValues(){
        for(Team t : teams){
            t.setLoses(0);
            t.setWins(0);
        }
    }

    public boolean isSeasonComplete(){
        for(Team t : teams){
            if(t.getLoses() + t.getWins() < numRegularGames)
                return false;
        }
        return true;
    }

    public String winnerPlayoffSeries(String team1, String team2){
        for(Team t : teams){
            if(t.getName().equals(team1) || t.getName().equals(team2)){
                if(t.getWins() >= numPlayoffGames) return t.getName();
            }
        }
        return null;
    }
}
