package com.gngg.gamewebsite.models;


public class Team {
    String mName;
    Integer mLoses = 0;
    Integer mWins = 0;

    public Team(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public Integer getLoses() {
        return mLoses;
    }

    public Integer getWins() {
        return mWins;
    }

    public void setName(String name) {
        mName = name;
    }

    public void setLoses(Integer loses) {
        mLoses = loses;
    }

    public void setWins(Integer wins) {
        mWins = wins;
    }

    public void incrementWinOrLoss(boolean didWin){
       if(didWin) mWins++;
       else mLoses++;
    }
}
