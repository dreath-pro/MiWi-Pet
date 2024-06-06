package com.example.miwipet.models.objects;

import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.utils.Rarity;

public class Backpack extends ObjectModel {
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Backpack()
    {
        super("object_backpack", "Backpack", 75, 250, 10, 1, 2, 1);
        setRarity(fixedRarity.getRarity(2));
    }
}
