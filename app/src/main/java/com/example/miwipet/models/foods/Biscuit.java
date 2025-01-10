package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Biscuit extends FoodModel {
    private int reference = R.drawable.food_biscuit;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Biscuit()
    {
        super("food_biscuit", "Biscuit", 10, 5, 100, 40);
        setRarity(fixedRarity.getRarity(3));
    }
}
