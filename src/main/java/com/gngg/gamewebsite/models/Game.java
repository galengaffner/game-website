package com.gngg.gamewebsite.models;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private String mName;
    private int mNumTeams;
    private List<String> mTeamNames;

    public Game() {
        mName = "GrapSheebMacWhaley";
        mNumTeams = 2;
        mTeamNames = new ArrayList<>();
        mTeamNames.add("Team 1");
        mTeamNames.add("Team 2");
    }

    public Game(String name, int numTeams, List<String> teamNames) {
        mName = name;
        mNumTeams = numTeams;
        mTeamNames = teamNames;
    }

    public String getName() {
        return mName;
    }

    public int getNumTeams() {
        return mNumTeams;
    }

    public List<String> getTeamNames() {
        return mTeamNames;
    }
}
