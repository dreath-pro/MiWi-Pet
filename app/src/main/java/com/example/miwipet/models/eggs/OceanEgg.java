package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class OceanEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.fish, R.drawable.pufferfish, R.drawable.stingray, R.drawable.clownfish,
                    R.drawable.squid, R.drawable.clam, R.drawable.seahorse},

            {R.drawable.angelfish, R.drawable.nautilus, R.drawable.starfish, R.drawable.lionfish,
                    R.drawable.shrimp, R.drawable.crab, R.drawable.lobster},

            {R.drawable.sea_turtle, R.drawable.swordfish, R.drawable.jellyfish, R.drawable.barracuda,
                    R.drawable.flying_fish, R.drawable.humphead_wrasse, R.drawable.hammerhead_shark},

            {R.drawable.tuna, R.drawable.shark, R.drawable.whale_shark, R.drawable.octopus,
                    R.drawable.dolphin, R.drawable.anglerfish, R.drawable.oarfish},

            {R.drawable.space_whale, R.drawable.terror_shark, R.drawable.devil_kraken,
                    R.drawable.mecha_sperm_whale}
    };

    private final String[][] petImages = {
            {"fish", "pufferfish", "stingray", "clownfish",
                    "squid", "clam", "seahorse"},


            {"angelfish", "nautilus", "starfish", "lionfish",
                    "shrimp", "crab", "lobster"},


            {"sea_turtle", "swordfish", "jellyfish", "barracuda",
                    "flying_fish", "humphead_wrasse", "hammerhead_shark"},


            {"tuna", "shark", "whale_shark", "octopus",
                    "dolphin", "anglerfish", "oarfish"},


            {"space_whale", "terror_shark", "devil_kraken",
                    "mecha_sperm_whale"}
    };

    private final String[][] petNames = {
            {"Fish", "Pufferfish", "Stingray", "Clownfish", "Squid", "Clam", "Seahorse"},

            {"Angelfish", "Nautilus", "Starfish", "Lionfish", "Shrimp", "Crab", "Lobster"},

            {"Sea Turtle", "Swordfish", "Jellyfish", "Barracuda", "Flying Fish",
                    "Humphead Wrasse", "Hammerhead Shark"},

            {"Tuna", "Shark", "Whale Shark", "Octopus", "Dolphin", "Anglerfish", "Oarfish"},

            {"Space Whale", "Terror Shark", "Devil Kraken", "Mecha Sperm Whale"}
    };

    public OceanEgg() {
        super("Ocean Egg", "egg_ocean", 30, 0, 0, 25, 0, 100);
        super.selectRarity(petImages, petNames);
    }
}
