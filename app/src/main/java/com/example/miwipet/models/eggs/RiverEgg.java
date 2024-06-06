package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class RiverEgg extends EggModel {
    private final int[][] references = {
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

    private final String[][] petImages = {
            {"firefly", "bass", "frog", "duck",
                    "otter"},


            {"salmon", "goose", "capybara", "hippo",
                    "salamander"},


            {"swan", "turtle", "electric_eel", "piranha",
                    "koi"},


            {"catfish", "pink_dolphin", "axolotl", "bull_shark",
                    "crocodile"},


            {"ancient_turtle", "majestic_koi", "waterfall_turtle"}
    };

    private final String[][] petNames = {
            {"Firefly", "Bass", "Frog", "Duck", "Otter"},

            {"Salmon", "Goose", "Capybara", "Hippo", "Salamander"},

            {"Swan", "Turtle", "Electric Eel", "Piranha", "Koi"},

            {"Catfish", "Pink Dolphin", "Axolotl", "Bull Shark", "Crocodile"},

            {"Ancient Turtle", "Majestic Koi", "Waterfall Turtle"}
    };

    public RiverEgg() {
        super("River Egg", "egg_river", 20, 0, 0, 50, 1, 75);
        super.selectRarity(petImages, petNames);
    }
}
