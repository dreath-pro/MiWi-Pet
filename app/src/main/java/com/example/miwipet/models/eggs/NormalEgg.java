package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class NormalEgg extends EggModel {
    private final String[][] petImages = {
            {"cockroach", "fly", "mosquito", "slug",
                    "grasshopper", "bee", "butterfly", "mantis"},


            {"blue_chick", "monarch_butterfly", "dragonfly", "chicken",
                    "lizard", "lady_bug", "bird", "caterpillar"},


            {"cat", "dog", "sheep", "rabbit",
                    "wolf", "donkey", "toucan", "cockatoo"},


            {"cow", "buffalo", "boar", "chameleon",
                    "pig", "parrot", "fox", "horse"},


            {"unicorn", "pegasus", "undead_unicorn"}
    };

    private final String[][] petNames = {
            {"Cockroach", "Fly", "Mosquito", "Slug", "Grasshopper", "Bee", "Butterfly", "Mantis"},

            {"Blue Chick", "Monarch Butterfly", "Dragonfly", "Chicken", "Lizard", "Lady Bug",
                    "Bird", "Caterpillar"},

            {"Cat", "Dog", "Sheep", "Rabbit", "Wolf", "Donkey", "Toucan", "Cockatoo"},

            {"Cow", "Buffalo", "Boar", "Chameleon", "Pig", "Parrot", "Fox", "Horse"},

            {"Unicorn", "Pegasus", "Undead Unicorn"}
    };

    public NormalEgg() {
        super("Normal Egg", "egg_normal", 10, 0, 0, 5, 0, 100);
        super.selectRarity(petImages, petNames);
    }
}
