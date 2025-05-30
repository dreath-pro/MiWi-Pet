package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Noodle extends FoodModel {
    private int reference = R.drawable.food_noodle;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Noodle()
    {
        super("food_noodle", "Noodle", 25, 70, 30, 0);
        setRarity(fixedRarity.getRarity(2));
    }
}
