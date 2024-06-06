package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ForestEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.ant, R.drawable.beaver, R.drawable.panther, R.drawable.spider,
                    R.drawable.squirrel, R.drawable.woodpecker},

            {R.drawable.owl, R.drawable.monkey, R.drawable.deer, R.drawable.snake,
                    R.drawable.bat, R.drawable.orangutan},

            {R.drawable.bear, R.drawable.gorilla, R.drawable.moose, R.drawable.sloth,
                    R.drawable.hawk, R.drawable.koala},

            {R.drawable.albino_tiger, R.drawable.sugar_glider, R.drawable.tiger, R.drawable.peacock,
                    R.drawable.kangaroo, R.drawable.panda},

            {R.drawable.galactic_owl, R.drawable.dire_wolf, R.drawable.dark_deer}
    };

    private final String[][] petImages = {
            {"ant", "beaver", "panther", "spider",
                    "squirrel", "woodpecker"},


            {"owl", "monkey", "deer", "snake",
                    "bat", "orangutan"},


            {"bear", "gorilla", "moose", "sloth",
                    "hawk", "koala"},


            {"albino_tiger", "sugar_glider", "tiger", "peacock",
                    "kangaroo", "panda"},


            {"galactic_owl", "dire_wolf", "dark_deer"}
    };

    private final String[][] petNames = {
            {"Ant", "Beaver", "Panther", "Spider", "Squirrel", "Woodpecker"},

            {"Owl", "Monkey", "Deer", "Snake", "Bat", "Orangutan"},

            {"Bear", "Gorilla", "Moose", "Sloth", "Hawk", "Koala"},

            {"Albino Tiger", "Sugar Glider", "Tiger", "Peacock", "Kangaroo", "Panda"},

            {"Galactic Owl", "Dire Wolf", "Dark Deer"}
    };

    public ForestEgg() {
        super("Forest Egg", "egg_forest", 20, 0, 0, 30, 0, 100);
        super.selectRarity(petImages, petNames);
    }
}
