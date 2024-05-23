package com.example.miwipet.models.eggs;

import com.example.miwipet.models.EggModel;

public class MythicEgg extends EggModel {
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
