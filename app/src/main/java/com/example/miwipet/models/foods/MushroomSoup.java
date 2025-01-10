package com.example.miwipet.models.foods;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.logics.Rarity;

public class MushroomSoup extends FoodModel {
    private int reference = R.drawable.food_mushroom_soup;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public MushroomSoup()
    {
        super("food_mushroom_soup", "Mushroom Soup", 50, 30, 40, 20);
        setRarity(fixedRarity.getRarity(2));
    }
}
