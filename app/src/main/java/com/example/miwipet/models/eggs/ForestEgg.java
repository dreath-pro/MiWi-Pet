package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ForestEgg extends EggModel {
    int[][] pets = {
            {R.drawable.bat, R.drawable.beaver, R.drawable.snake, R.drawable.spider,
                    R.drawable.squirrel, R.drawable.woodpecker},


            {R.drawable.owl, R.drawable.monkey, R.drawable.deer, R.drawable.panther},


            {R.drawable.bear, R.drawable.gorilla, R.drawable.moose, R.drawable.sloth},


            {R.drawable.albino_tiger, R.drawable.sugar_glider, R.drawable.tiger},


            {R.drawable.galactic_owl, R.drawable.golden_tiger, R.drawable.vampire_bat}
    };

    String[][] petNames = {
            {"Bat", "Beaver", "Snake", "Spider", "Squirrel", "Woodpecker"},

            {"Owl", "Monkey", "Deer", "Panther"},

            {"Bear", "Gorilla", "Moose", "Sloth"},

            {"Albino Tiger", "Sugar Glider", "tiger"},

            {"Galactic Owl", "Golden Tiger", "Vampire Bat"}
    };

    public ForestEgg() {
        super("Forest Egg", R.drawable.egg_forest, 0, 30, 0);
        super.selectRarity(pets, petNames);
    }
}
