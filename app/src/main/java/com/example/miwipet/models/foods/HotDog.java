package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class HotDog extends FoodModel {
    private int reference = R.drawable.food_hot_dog;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public HotDog()
    {
        super("food_hot_dog", "Hot Dog", 20, 75, 30, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
