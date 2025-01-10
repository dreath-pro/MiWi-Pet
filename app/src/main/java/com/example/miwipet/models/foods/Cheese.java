package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Cheese extends FoodModel {
    private int reference = R.drawable.food_cheese;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Cheese()
    {
        super("food_cheese", "Cheese", 20, 95, 20, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
