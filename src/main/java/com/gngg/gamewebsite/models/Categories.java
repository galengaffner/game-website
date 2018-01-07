package com.gngg.gamewebsite.models;

import java.util.Random;

public enum  Categories {

    CreativeCat("Creative Cat"),
    StarPerformer("Star Performer"),
    Taboo("Taboo"),
    TriviaPursuit("Trivia Pursuit"),
    Guesstures("Guesstures"),
    MovieQuote("Movie Quote"),
    Scattegories("Scattegories");

    private final String name;

    Categories(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    Categories getRandomName() {
        Random rand = new Random();
        Categories[] names = this.values();
        int index = rand.nextInt(names.length);
        return names[index];
    }
}
