package com.gngg.gamewebsite.models;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.*;

public class FirstGame{
    @NotNull(message = "Must enter a number")
    @Min(value = 1, message = "Must have a value greater than 0")
    private Integer numRegularGames;

    @NotNull(message = "Must enter a number")
    @Min(value = 1, message = "Must have a value greater than 0")
    private Integer numPlayoffGames;

    @NotNull(message = "Must enter a number")
    @Min(value = 2, message = "Must have a value greater than 2")
    @Max(value = 8, message = "No more than 8 teams allowed")
    private Integer numTeams;

    private List<Team> teams;
    private Categories category = Categories.CreativeCat;
    private List<PlayoffMatchup> playoffMatchups;
    private Team winner;

    public FirstGame() {
        this(2, 10, 4);
    }

    public FirstGame(int numTeams, int numRegularGames, int numPlayoffGames) {
        this.numTeams = numTeams;
        this.numRegularGames = numRegularGames;
        this.numPlayoffGames = numPlayoffGames;
        setNumTeams(numTeams);
    }

    public Team getWinner() {
        return winner;
    }

    public void setNumRegularGames(Integer numRegularGames) {
        this.numRegularGames = numRegularGames;
    }

    public void setNumPlayoffGames(Integer numPlayoffGames) {
        this.numPlayoffGames = numPlayoffGames;
    }

    public void setNumTeams(Integer numTeams) {
        this.numTeams = numTeams;
        teams = new ArrayList<>();
        for(int i = 0; i < this.numTeams; i++){
            teams.add(new Team(String.format("Team %s", i + 1)));
        }
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

    public List<Team> getTeams() { return teams; }

    public void setTeams(List<Team> teams) { this.teams = teams; }

    public List<PlayoffMatchup> getPlayoffMatchups() { return playoffMatchups; }

    public String getRandomCategory(){
        category = category.getRandomName();
        return  category.getName();
    }

    public boolean gameResult(String team, boolean didWin){
        for(Team t : teams){
            if(t.name.equals(team)){
                t.incrementWinOrLoss(didWin, numRegularGames);
                break;
            }
        }
        return isSeasonComplete();
    }

    private boolean isSeasonComplete(){
        for(Team t : teams){
            if(t.losses + t.wins < numRegularGames) {
                return false;
            }
        }
        createPlayoffMatchups();
        return true;
    }

    public boolean updatePlayoffMatchup(String team, boolean didWin) {
        for (Team t : teams) {
            if (t.name.equals(team)) {
                t.incrementPlayoffWinOrLoss(didWin, numPlayoffGames);
                break;
            }
        }
        boolean ret = doWeHaveAWinner();
        if(!ret) advancePlayoffs();
        return ret;
    }

    private void advancePlayoffs(){
        for (PlayoffMatchup matchup : playoffMatchups){
            if(!matchup.isComplete()) {
                Team t1 = matchup.getTeam1();
                Team t2 = matchup.getTeam2();
                if (t1 != null && t2 != null) {
                    if (t1.playoffWins >= numPlayoffGames || t2.playoffWins >= numPlayoffGames) {
                        matchup.setComplete(true);
                        for (PlayoffMatchup m : playoffMatchups){
                            if(m.getTeam1FromId() == matchup.getMatchupID()){
                                m.setTeam1(t1.playoffWins > t2.playoffWins ? t1:t2);
                            }
                            else if(m.getTeam2FromId() == matchup.getMatchupID()){
                                m.setTeam2(t1.playoffWins > t2.playoffWins ? t1:t2);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean doWeHaveAWinner(){
        Team t1 = playoffMatchups.get(playoffMatchups.size() - 1).getTeam1();
        Team t2 = playoffMatchups.get(playoffMatchups.size() - 1).getTeam2();
        if(t1 == null || t2 == null){
            return false;
        }
        else{
            if(t1.playoffWins >= numPlayoffGames){
                winner = t1;
                return true;
            }
            else if(t2.playoffWins >= numPlayoffGames){
                winner = t2;
                return true;
            }
        }
        return false;
    }

    private void createPlayoffMatchups(){
        // Sort the teams based on wins
        Collections.sort(teams, new Comparator<Team>(){
            public int compare(Team o1, Team o2){
                if(o1.wins == o2.wins){
                    Random rand = new Random();
                    if(rand.nextInt(1) == 0) return -1;
                    else return 1;
                }
                return o1.wins < o2.wins ? 1 : -1;
            }
        });

        // Seed each team and set make sure they are not eliminated
        int seed = 1;
        for(Team t : teams){
            t.playoffSeed = seed;
            seed++;
        }

        // Create the list of playoff matchups
        playoffMatchups = new ArrayList<>();
        PlayoffMatchup matchup;
        switch(numTeams){
            case 2:
                playoffMatchups.add(new PlayoffMatchup(teams.get(0), teams.get(1), numPlayoffGames, 1, 1));
                break;
            case 3:
                playoffMatchups.add(new PlayoffMatchup(teams.get(1), teams.get(2), numPlayoffGames, 1, 1));
                matchup = new PlayoffMatchup(numPlayoffGames, 2, 2, 0, 1);
                matchup.setTeam1(teams.get(0));
                playoffMatchups.add(matchup);
                break;
            case 4:
                playoffMatchups.add(new PlayoffMatchup(teams.get(0), teams.get(3), numPlayoffGames, 1, 1));
                playoffMatchups.add(new PlayoffMatchup(teams.get(1), teams.get(2), numPlayoffGames, 1, 2));
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 2, 3, 1, 2));
                break;
            case 5:
                playoffMatchups.add(new PlayoffMatchup(teams.get(1), teams.get(2), numPlayoffGames, 1, 1));
                playoffMatchups.add(new PlayoffMatchup(teams.get(3), teams.get(4), numPlayoffGames, 1, 2));
                matchup = new PlayoffMatchup(numPlayoffGames, 2, 3, 0, 2);
                matchup.setTeam1(teams.get(0));
                playoffMatchups.add(matchup);
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 3, 4, 3, 1));
                break;
            case 6:
                playoffMatchups.add(new PlayoffMatchup(teams.get(3), teams.get(4), numPlayoffGames, 1, 1));
                playoffMatchups.add(new PlayoffMatchup(teams.get(2), teams.get(5), numPlayoffGames, 1, 2));
                matchup = new PlayoffMatchup(numPlayoffGames, 2, 3, 0, 1);
                matchup.setTeam1(teams.get(0));
                playoffMatchups.add(matchup);
                matchup = new PlayoffMatchup(numPlayoffGames, 2, 4, 0, 2);
                matchup.setTeam1(teams.get(1));
                playoffMatchups.add(matchup);
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 3, 5, 3, 4));
                break;
            case 7:
                playoffMatchups.add(new PlayoffMatchup(teams.get(3), teams.get(4), numPlayoffGames, 1, 1));
                playoffMatchups.add(new PlayoffMatchup(teams.get(2), teams.get(5), numPlayoffGames, 1, 2));
                playoffMatchups.add(new PlayoffMatchup(teams.get(1), teams.get(6), numPlayoffGames, 1, 3));
                matchup = new PlayoffMatchup(numPlayoffGames, 2, 4, 0, 1);
                matchup.setTeam1(teams.get(0));
                playoffMatchups.add(matchup);
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 2, 5, 2, 3));
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 3, 6, 4, 5));
                break;
            case 8:
                playoffMatchups.add(new PlayoffMatchup(teams.get(0), teams.get(7), numPlayoffGames, 1, 1));
                playoffMatchups.add(new PlayoffMatchup(teams.get(3), teams.get(4), numPlayoffGames, 1, 2));
                playoffMatchups.add(new PlayoffMatchup(teams.get(2), teams.get(5), numPlayoffGames, 1, 3));
                playoffMatchups.add(new PlayoffMatchup(teams.get(1), teams.get(6), numPlayoffGames, 1, 4));
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 2, 5, 1, 2));
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 2, 6, 3, 4));
                playoffMatchups.add(new PlayoffMatchup(numPlayoffGames, 3, 7, 5, 6));
                break;
        }
    }
}
