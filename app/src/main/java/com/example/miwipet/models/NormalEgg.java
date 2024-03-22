package com.example.miwipet.models;

import com.example.miwipet.R;

import java.util.ArrayList;

public class NormalEgg extends EggModel {
    int[][] pets = {
            {R.drawable.butterfly, R.drawable.cat, R.drawable.blue_chick, R.drawable.dog,
                    R.drawable.elephant, R.drawable.parrot, R.drawable.rhinoceros},


            {R.drawable.chicken, R.drawable.flamingo, R.drawable.owl, R.drawable.reindeer,
                    R.drawable.cow, R.drawable.snake, R.drawable.pig},


            {R.drawable.bear, R.drawable.crocodile, R.drawable.lion, R.drawable.piranha,
                    R.drawable.shark, R.drawable.tiger, R.drawable.whale_shark},


            {R.drawable.megalodon, R.drawable.mosasaur, R.drawable.pteranodon, R.drawable.smilodon,
                    R.drawable.stegosaurus, R.drawable.t_rex, R.drawable.triceratops},


            {R.drawable.hydra, R.drawable.minotaur, R.drawable.phoenix, R.drawable.rock_golem,
                    R.drawable.shadow_dragon, R.drawable.sphinx, R.drawable.white_dragon}
    };

    public NormalEgg() {
        super("Normal Egg", R.drawable.white_egg, 30, 20, 0);
        super.selectRarity(pets);
    }
}
