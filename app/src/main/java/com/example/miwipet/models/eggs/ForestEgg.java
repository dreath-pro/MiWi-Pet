package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ForestEgg extends EggModel {
    int[][] pets = {
            {R.drawable.ant, R.drawable.beaver, R.drawable.panther, R.drawable.spider,
                    R.drawable.squirrel, R.drawable.woodpecker},


            {R.drawable.owl, R.drawable.monkey, R.drawable.deer, R.drawable.snake,
                    R.drawable.bat, R.drawable.orangutan},


            {R.drawable.bear, R.drawable.gorilla, R.drawable.moose, R.drawable.sloth,
                    R.drawable.hawk, R.drawable.koala},


            {R.drawable.albino_tiger, R.drawable.sugar_glider, R.drawable.tiger, R.drawable.peacock,
                    R.drawable.kangaroo, R.drawable.panda},


            {R.drawable.galactic_owl, R.drawable.guardian_deer, R.drawable.dark_deer}
    };

    String[][] petNames = {
            {"Ant", "Beaver", "Panther", "Spider", "Squirrel", "Woodpecker"},

            {"Owl", "Monkey", "Deer", "Snake", "Bat", "Orangutan"},

            {"Bear", "Gorilla", "Moose", "Sloth", "Hawk", "Koala"},

            {"Albino Tiger", "Sugar Glider", "Tiger", "Peacock", "Kangaroo", "Panda"},

            {"Galactic Owl", "Guardian Deer", "Dark Deer"}
    };

    public ForestEgg() {
        super("Forest Egg", R.drawable.egg_forest, 20, 0, 0, 30, 0, 100);
        super.selectRarity(pets, petNames);
    }
}
