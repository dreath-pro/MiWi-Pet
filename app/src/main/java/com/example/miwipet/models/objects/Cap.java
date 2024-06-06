package com.example.miwipet.models.objects;

import com.example.miwipet.R;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.utils.Rarity;

public class Cap extends ObjectModel {
    private int reference = R.drawable.object_cap;
    private Rarity fixedRarity = new Rarity();

    /**
     * 0 - common
     * 1 - rare
     * 2 - ultra
     * 3 - legendary
     * 4 - mythic
     */

    public Cap()
    {
        super("object_cap", "Cap", 100, 150, 0, 0, 2, 0);
        setRarity(fixedRarity.getRarity(0));
    }
}
