package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Broccoli extends FoodModel {
    private int reference = R.drawable.food_broccoli;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Broccoli()
    {
        super("food_broccoli", "Broccoli", 50, 25, 50, 10);
        setRarity(fixedRarity.getRarity(3));
    }
}
