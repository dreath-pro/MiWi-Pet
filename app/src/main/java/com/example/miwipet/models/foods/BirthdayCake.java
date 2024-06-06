package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

public class BirthdayCake extends FoodModel {
    private int reference = R.drawable.food_birthday_cake;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public BirthdayCake()
    {
        super("food_birthday_cake", "Birthday Cake", 100, 1, 30, 10);
        setRarity(fixedRarity.getRarity(4));
    }
}
