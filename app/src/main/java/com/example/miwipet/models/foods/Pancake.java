package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Pancake extends FoodModel {
    private int reference = R.drawable.food_pancake;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Pancake()
    {
        super("food_pancake", "Pancake", 50, 5, 50, 10);
        setRarity(fixedRarity.getRarity(4));
    }
}
