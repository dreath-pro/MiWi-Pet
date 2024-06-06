package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Meat extends FoodModel {
    private int reference = R.drawable.food_meat;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Meat()
    {
        super("food_meat", "Meat", 30, 50, 30, 0);
        setRarity(fixedRarity.getRarity(3));
    }
}
