package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class Lollipop extends FoodModel {
    private int reference = R.drawable.food_lollipop;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Lollipop()
    {
        super("food_lollipop", "Lollipop", 90, 1, 100, 50);
        setRarity(fixedRarity.getRarity(4));
    }
}
