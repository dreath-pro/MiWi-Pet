package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class IceCream extends FoodModel {
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public IceCream()
    {
        super("food_ice_cream", "Ice Cream", 15, 85, 20, 0);
        setRarity(fixedRarity.getRarity(1));
    }
}
