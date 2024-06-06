package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Milk extends FoodModel {
    private int reference = R.drawable.food_milk;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Milk()
    {
        super("food_milk", "Milk", 15, 65, 10, 0);
        setRarity(fixedRarity.getRarity(2));
    }
}
