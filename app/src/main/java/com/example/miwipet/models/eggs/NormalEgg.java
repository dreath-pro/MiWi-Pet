package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class NormalEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.cockroach, R.drawable.fly, R.drawable.mosquito, R.drawable.slug,
                    R.drawable.grasshopper, R.drawable.bee, R.drawable.butterfly, R.drawable.mantis},

            {R.drawable.blue_chick, R.drawable.monarch_butterfly, R.drawable.dragonfly, R.drawable.chicken,
                    R.drawable.lizard, R.drawable.lady_bug, R.drawable.bird, R.drawable.caterpillar},

            {R.drawable.cat, R.drawable.dog, R.drawable.sheep, R.drawable.rabbit,
                    R.drawable.wolf, R.drawable.donkey, R.drawable.toucan, R.drawable.cockatoo},

            {R.drawable.cow, R.drawable.buffalo, R.drawable.boar, R.drawable.chameleon,
                    R.drawable.pig, R.drawable.parrot, R.drawable.fox, R.drawable.horse},

            {R.drawable.unicorn, R.drawable.pegasus, R.drawable.undead_unicorn}
    };

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
