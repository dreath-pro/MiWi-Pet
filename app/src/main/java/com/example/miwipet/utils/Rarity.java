package com.example.miwipet.utils;

import com.example.miwipet.R;

public class Rarity {
    private String[] rarities = new String[]{"Common", "Rare", "Ultra", "Legendary", "Mythic"};
    private int[] rarityColors = new int[]{R.color.common, R.color.uncommon,
            R.color.rare, R.color.legendary,
            R.color.mythic};
    private int[] typeColors = new int[]{R.color.normal, R.color.crystal, R.color.gemstone};

    public Rarity() {

    }

    public String getRarity(int rarityIndex) {
        return rarities[rarityIndex];
    }

    public int getRarityColor(int rarityIndex) {
        return rarityColors[rarityIndex];
    }

    public int getTypeColor(int typeIndex) {
        return typeColors[typeIndex];
    }
}
