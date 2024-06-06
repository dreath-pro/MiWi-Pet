package com.example.miwipet.models.objects;

import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.utils.Rarity;

public class FlowerPot extends ObjectModel {
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public FlowerPot()
    {
        super("object_flower_pot", "Flower Pot", 90, 200, 0, 1, 0, 0);
        setRarity(fixedRarity.getRarity(1));
    }
}
