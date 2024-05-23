package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class RiverEgg extends EggModel {
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
