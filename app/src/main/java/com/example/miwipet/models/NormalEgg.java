package com.example.miwipet.models;

import com.example.miwipet.R;

public class NormalEgg extends EggModel {
    int[][] pets = {
            {R.drawable.cockroach, R.drawable.fly, R.drawable.mosquito, R.drawable.slug},


            {R.drawable.butterfly, R.drawable.monarch_butterfly, R.drawable.dragonfly, R.drawable.grasshopper,
                    R.drawable.lizard, R.drawable.lady_bug, R.drawable.bee, R.drawable.caterpillar},


            {R.drawable.cat, R.drawable.dog, R.drawable.bird, R.drawable.rabbit,
                    R.drawable.blue_chick, R.drawable.donkey, R.drawable.chicken, R.drawable.cockatoo},


            {R.drawable.cow, R.drawable.musk_ox, R.drawable.buffalo, R.drawable.sheep,
                    R.drawable.pig, R.drawable.parrot, R.drawable.fox, R.drawable.horse},


            {R.drawable.unicorn, R.drawable.pegasus}
    };

    public NormalEgg() {
        super("Normal Egg", R.drawable.egg_normal, 30, 20, 0);
        super.selectRarity(pets);
    }
}
