package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Smoothie extends FoodModel {
    private int reference = R.drawable.food_smoothie;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Smoothie()
    {
        super("food_smoothie", "Smoothie", 100, 1, 50, 10);
        setRarity(fixedRarity.getRarity(4));
    }
}
