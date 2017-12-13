package com.gngg.gamewebsite.models;

import java.util.*;

public class FirstGame extends Game{
    private int mNumRegularGames;
    private int mNumPlayoffGames;
    private Map<String, Integer> mTeamWins = new HashMap<>();
    private Map<String, Integer> mTeamLoses = new HashMap<>();
    private List<String> mCategories = new ArrayList<>();

    public FirstGame(String name, int numTeams, List<String> teamNames, int numRegularGames, int numPlayoffGames) {
        super(name, numTeams, teamNames);
        mNumRegularGames = numRegularGames;
        mNumPlayoffGames = numPlayoffGames;
        for(String team : teamNames){
            mTeamWins.put(team, 0);
        }
        mCategories.add("Creative Cat");
        mCategories.add("Star Performer");
        mCategories.add("Taboo");
        mCategories.add("Trivia Pursuit");
        mCategories.add("Guesstures");
    }

    public int getNumRegularGames() {
        return mNumRegularGames;
    }

    public int getNumPlayoffGames() {
        return mNumPlayoffGames;
    }

    public String getRandomCategory(){
        Random rand = new Random();
        int index = rand.nextInt(mCategories.size() - 1);
        return mCategories.get(index);
    }

    public void gameResult(String teamName, boolean didWin){
        if(didWin){
            mTeamWins.put(teamName, mTeamWins.get(teamName) + 1);
        }
        else{
            mTeamLoses.put(teamName, mTeamLoses.get(teamName) + 1);
        }
    }

    public void gameResult(String teamName, boolean didWin, String vsName){
        gameResult(teamName, didWin);
        gameResult(vsName, !didWin);
    }

    public void resetWinLossValues(){
        for(String team : super.getTeamNames()){
            mTeamWins.put(team, 0);
            mTeamLoses.put(team, 0);
        }
    }

    public boolean isSeasonComplete(){
        for(String team : super.getTeamNames()) {
            if (mTeamLoses.get(team) + mTeamWins.get(team) < mNumRegularGames)
                return false;
        }
        return true;
    }

    public boolean isPlayoffSeriesComplete(String team1, String team2){
        if(mTeamWins.get(team1) >= mNumPlayoffGames || mTeamWins.get(team2) >= mNumPlayoffGames)
            return true;
        return false;
    }
}
