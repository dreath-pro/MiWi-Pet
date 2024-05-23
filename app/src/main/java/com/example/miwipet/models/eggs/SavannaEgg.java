package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class SavannaEgg extends EggModel {
    private final String[][] petImages = {
            {"elephant", "rhino", "warthog", "lemur",
                    "black_mamba", "meerkat"},


            {"zebra", "cheetah", "gazelle", "vine_snake",
                    "mandrill", "african_buffalo"},


            {"ostrich", "vulture", "wildebeest", "african_wild_dog",
                    "caracal", "impala"},


            {"giraffe", "hyena", "lion", "leopard",
                    "leopard_turtle", "lynx"},


            {"sun_war_lion", "death_vulture", "viper_snake"}
    };

    private final String[][] petNames = {
            {"Elephant", "Rhino", "Warthog", "Lemur", "Black Mamba", "Meerkat"},

            {"Zebra", "Cheetah", "Gazelle", "Vine Snake", "Mandrill", "African Buffalo"},

            {"Ostrich", "Vulture", "Wildebeest", "African Wild Dog", "Caracal", "Impala"},

            {"Giraffe", "Hyena", "Lion", "Leopard", "Leopard Turtle", "Lynx"},

            {"Sun War Lion", "Death Vulture", "Viper Snake"}
    };

    public SavannaEgg() {
        super("Savanna Egg", "egg_savanna", 30, 0, 0, 0, 3, 30);
        super.selectRarity(petImages, petNames);
    }
}
