package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class Juice extends FoodModel {
    private int reference = R.drawable.food_juice;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Juice()
    {
        super("food_juice", "Juice", 80, 5, 40, 10);
        setRarity(fixedRarity.getRarity(4));
    }
}
