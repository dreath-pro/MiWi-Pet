package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Apple extends FoodModel {
    private int reference = R.drawable.food_apple;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Apple()
    {
        super("food_apple", "Apple", 15, 80, 20, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
