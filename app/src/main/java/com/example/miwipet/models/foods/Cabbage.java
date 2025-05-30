package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Cabbage extends FoodModel {
    private int reference = R.drawable.food_cabbage;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Cabbage()
    {
        super("food_cabbage", "Cabbage", 45, 40, 40, 0);
        setRarity(fixedRarity.getRarity(3));
    }
}
