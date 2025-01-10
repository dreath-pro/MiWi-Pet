package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Burger extends FoodModel {
    private int reference = R.drawable.food_burger;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Burger()
    {
        super("food_burger", "Burger", 10, 100, 20, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
