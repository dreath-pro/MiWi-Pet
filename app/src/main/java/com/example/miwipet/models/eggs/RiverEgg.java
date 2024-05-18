package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class RiverEgg extends EggModel {
    int[][] pets = {
            {R.drawable.firefly, R.drawable.bass, R.drawable.frog, R.drawable.duck,
                    R.drawable.otter},


            {R.drawable.salmon, R.drawable.goose, R.drawable.capybara, R.drawable.hippo,
                    R.drawable.salamander},


            {R.drawable.swan, R.drawable.turtle, R.drawable.electric_eel, R.drawable.piranha,
                    R.drawable.koi},


            {R.drawable.catfish, R.drawable.pink_dolphin, R.drawable.axolotl, R.drawable.bull_shark,
                    R.drawable.crocodile},


            {R.drawable.ancient_turtle, R.drawable.majestic_koi, R.drawable.waterfall_turtle}
    };

    String[][] petNames = {
            {"Firefly", "Bass", "Frog", "Duck", "Otter"},

            {"Salmon", "Goose", "Capybara", "Hippo", "Salamander"},

            {"Swan", "Turtle", "Electric Eel", "Piranha", "Koi"},

            {"Catfish", "Pink Dolphin", "Axolotl", "Bull Shark", "Crocodile"},

            {"Ancient Turtle", "Majestic Koi", "Waterfall Turtle"}
    };

    public RiverEgg() {
        super("River Egg", R.drawable.egg_river, 20, 0, 0, 50, 1, 75);
        super.selectRarity(pets, petNames);
    }
}
