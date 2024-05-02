package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class SavannaEgg extends EggModel {
    int[][] pets = {
            {R.drawable.elephant, R.drawable.rhino},


            {R.drawable.zebra, R.drawable.cheetah, R.drawable.gazelle},


            {R.drawable.lion, R.drawable.vulture, R.drawable.wildebeest},


            {R.drawable.giraffe, R.drawable.hyena},


            {R.drawable.blazing_lion, R.drawable.death_vulture}
    };

    String[][] petNames = {
            {"Elephant", "Rhino"},

            {"Zebra", "Cheetah", "Gazelle"},

            {"Lion", "Vulture", "Wildebeest"},

            {"Giraffe", "Hyena"},

            {"Blazing Lion", "Death Vulture"}
    };

    public SavannaEgg() {
        super("Savanna Egg", R.drawable.egg_savanna, 50, 0, 0, 0, 3);
        super.selectRarity(pets, petNames);
    }
}
