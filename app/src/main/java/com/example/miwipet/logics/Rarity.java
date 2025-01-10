package com.example.miwipet.logics;

public class Rarity {
    private String[] rarities = new String[]{"Common", "Rare", "Ultra", "Legendary", "Mythic"};

    public Rarity() {

    }

    public String getRarity(int rarityIndex) {
        return rarities[rarityIndex];
    }
}
