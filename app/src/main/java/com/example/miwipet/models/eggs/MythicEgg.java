package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class MythicEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.wendigo, R.drawable.nessie},

            {R.drawable.hydra, R.drawable.minotaur, R.drawable.sphinx},

            {R.drawable.red_dragon, R.drawable.serpent},

            {R.drawable.jade_dragon, R.drawable.kitsune, R.drawable.griffin},

            {R.drawable.cerberus, R.drawable.monkey_king}
    };

    private final String[][] petImages = {
            {"wendigo", "nessie"},


            {"hydra", "minotaur", "sphinx"},


            {"red_dragon", "serpent"},


            {"jade_dragon", "kitsune", "griffin"},


            {"cerberus", "monkey_king"}
    };

    private final String[][] petNames = {
            {"Wendigo", "Nessie"},

            {"Hydra", "Minotaur", "Sphinx"},

            {"Red Dragon", "Serpent"},

            {"Jade Dragon", "Kitsune", "Griffin"},

            {"Cerberus", "Monkey King"}
    };

    public MythicEgg() {
        super("Mythic Egg", "egg_mythic", 30, 10, 0, 30, 3, 45);
        super.selectRarity(petImages, petNames);
    }
}
