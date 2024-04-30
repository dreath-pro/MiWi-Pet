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
                    R.drawable.hawk, R.drawable.kangaroo},


            {R.drawable.albino_tiger, R.drawable.sugar_glider, R.drawable.tiger, R.drawable.peacock},


            {R.drawable.galactic_owl, R.drawable.tree_deer, R.drawable.soul_dragon}
    };

    String[][] petNames = {
            {"Ant", "Beaver", "Panther", "Spider", "Squirrel", "Woodpecker"},

            {"Owl", "Monkey", "Deer", "Snake", "Bat", "Orangutan"},

            {"Bear", "Gorilla", "Moose", "Sloth", "Hawk", "Kangaroo"},

            {"Albino Tiger", "Sugar Glider", "Tiger", "Peacock"},

            {"Galactic Owl", "Tree Deer", "Soul Dragon"}
    };

    public ForestEgg() {
        super("Forest Egg", R.drawable.egg_forest, 20, 0, 0, 30, 0);
        super.selectRarity(pets, petNames);
    }
}
