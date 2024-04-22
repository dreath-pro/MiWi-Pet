package com.example.miwipet.utils;

public class RarityNames {
    private String[] rarities = new String[]{"Common", "Rare", "Ultra", "Legendary", "Mythic"};

    public RarityNames() {

    }

    public String getRarity(int rarityIndex)
    {
        return rarities[rarityIndex];
    }
}
