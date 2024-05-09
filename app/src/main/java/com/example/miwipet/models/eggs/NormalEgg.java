package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class NormalEgg extends EggModel {
    int[][] pets = {
            {R.drawable.cockroach, R.drawable.fly, R.drawable.mosquito, R.drawable.slug},


            {R.drawable.butterfly, R.drawable.monarch_butterfly, R.drawable.dragonfly, R.drawable.grasshopper,
                    R.drawable.lizard, R.drawable.lady_bug, R.drawable.bee, R.drawable.caterpillar},


            {R.drawable.cat, R.drawable.dog, R.drawable.bird, R.drawable.rabbit,
                    R.drawable.blue_chick, R.drawable.donkey, R.drawable.chicken, R.drawable.cockatoo},


            {R.drawable.cow, R.drawable.buffalo, R.drawable.musk_ox, R.drawable.sheep,
                    R.drawable.pig, R.drawable.parrot, R.drawable.fox, R.drawable.horse},


            {R.drawable.unicorn, R.drawable.pegasus}
    };

    String[][] petNames = {
            {"Cockroach", "Fly", "Mosquito", "Slug"},

            {"Butterfly", "Monarch Butterfly", "Dragonfly", "Grasshopper", "Lizard", "Lady Bug",
                    "Bee", "Caterpillar"},

            {"Cat", "Dog", "Bird", "Rabbit", "Blue Chick", "Donkey", "Chicken", "Cockatoo"},

            {"Cow", "Buffalo", "Musk Ox", "Sheep", "Pig", "Parrot", "Fox", "Horse"},

            {"Unicorn", "Pegasus"}
    };

    public NormalEgg() {
        super("Normal Egg", R.drawable.egg_normal, 10, 0, 0, 5, 0, 100);
        super.selectRarity(pets, petNames);
    }
}
