package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class OceanEgg extends EggModel {
    int[][] pets = {
            {R.drawable.fish, R.drawable.pufferfish, R.drawable.stingray, R.drawable.clownfish,
                    R.drawable.squid, R.drawable.clam},


            {R.drawable.angelfish, R.drawable.nautilus, R.drawable.starfish, R.drawable.lionfish,
                    R.drawable.shrimp, R.drawable.crab},


            {R.drawable.turtle, R.drawable.lobster, R.drawable.swordfish, R.drawable.jellyfish,
                    R.drawable.barracuda, R.drawable.flying_fish},


            {R.drawable.tuna, R.drawable.shark, R.drawable.whale_shark, R.drawable.octopus,
                    R.drawable.dolphin, R.drawable.anglerfish},


            {R.drawable.space_whale, R.drawable.ghost_shark, R.drawable.hollow_squid,
                    R.drawable.goldfish}
    };

    String[][] petNames = {
            {"Fish", "Puffer Fish", "Stingray", "Clownfish", "Squid", "Clam"},

            {"Angelfish", "Nautilus", "Starfish", "Lionfish", "Shrimp", "Crab"},

            {"Turtle", "Lobster", "Swordfish", "Jellyfish", "Barracuda", "Flying Fish"},

            {"Tuna", "Shark", "Whale Shark", "Octopus", "Dolphin", "Anglerfish"},

            {"Space Whale", "Ghost Shark", "Hollow Squid", "Goldfish"}
    };

    public OceanEgg() {
        super("Ocean Egg", R.drawable.egg_ocean, 30, 0, 0, 25, 0);
        super.selectRarity(pets, petNames);
    }
}
