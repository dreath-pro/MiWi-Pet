package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class OceanEgg extends EggModel {
    int[][] pets = {
            {R.drawable.fish, R.drawable.pufferfish, R.drawable.stingray, R.drawable.clownfish,
                    R.drawable.squid},


            {R.drawable.angelfish, R.drawable.jellyfish, R.drawable.starfish, R.drawable.lionfish,
                    R.drawable.shrimp},


            {R.drawable.turtle, R.drawable.lobster, R.drawable.swordfish, R.drawable.nautilus,
                    R.drawable.barracuda},


            {R.drawable.tuna, R.drawable.shark, R.drawable.whale_shark, R.drawable.octopus},


            {R.drawable.space_whale, R.drawable.ghost_shark, R.drawable.hollow_squid,
                    R.drawable.goldfish}
    };

    String[][] petNames = {
            {"Fish", "Puffer Fish", "Stingray", "Clownfish", "Squid"},

            {"Angelfish", "Jellyfish", "Starfish", "Lionfish", "Shrimp"},

            {"Turtle", "Lobster", "Swordfish", "Nautilus", "Barracuda"},

            {"Tuna", "Shark", "Whale Shark", "Octopus"},

            {"Space Whale", "Ghost Shark", "Hollow Squid", "Goldfish"}
    };

    public OceanEgg() {
        super("Ocean Egg", R.drawable.egg_ocean, 0, 25, 0);
        super.selectRarity(pets, petNames);
    }
}
