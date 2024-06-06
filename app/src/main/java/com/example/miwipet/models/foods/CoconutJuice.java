package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class CoconutJuice extends FoodModel {
    private int reference = R.drawable.food_coconut_juice;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public CoconutJuice()
    {
        super("food_coconut_juice", "Coconut Juice", 10, 20, 30, 0);
        setRarity(fixedRarity.getRarity(2));
    }
}
