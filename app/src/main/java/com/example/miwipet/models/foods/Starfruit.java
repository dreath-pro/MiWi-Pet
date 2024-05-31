package com.example.miwipet.models.foods;

import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Starfruit extends FoodModel {
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Starfruit()
    {
        super("food_starfruit", "Starfruit", 10, 5, 50, 20);
        setRarity(fixedRarity.getRarity(3));
    }
}
