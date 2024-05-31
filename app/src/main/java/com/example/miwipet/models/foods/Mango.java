package com.example.miwipet.models.foods;

import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Mango extends FoodModel {
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Mango()
    {
        super("food_mango", "Mango", 85, 10, 80, 20);
        setRarity(fixedRarity.getRarity(4));
    }
}
