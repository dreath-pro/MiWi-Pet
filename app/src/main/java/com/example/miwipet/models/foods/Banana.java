package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Banana extends FoodModel {
    private int reference = R.drawable.food_banana;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Banana()
    {
        super("food_banana", "Banana", 15, 95, 20, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
