package com.example.miwipet.utils;

import com.example.miwipet.R;

public class Rarity {
    private String[] rarities = new String[]{"Common", "Rare", "Ultra", "Legendary", "Mythic"};

    public Rarity() {

    }

    public String getRarity(int rarityIndex) {
        return rarities[rarityIndex];
    }
}
