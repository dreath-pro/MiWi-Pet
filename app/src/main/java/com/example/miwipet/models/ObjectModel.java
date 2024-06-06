package com.example.miwipet.models;

import com.example.miwipet.R;
import com.example.miwipet.utils.Rarity;

public class ObjectModel {
    private int id;
    private String objectImage;
    private String objectName;
    private String rarity;
    private int objectChance;
    private int chipPrice, glazePrice;
    private int timeBuff, chipBuff, glazeBuff;

    private Rarity rarityClass = new Rarity();
    private String[] rarities = new String[]{
            rarityClass.getRarity(0),
            rarityClass.getRarity(1),
            rarityClass.getRarity(2),
            rarityClass.getRarity(3),
            rarityClass.getRarity(4),};

    private int[] rarityBackgrounds = new int[]{R.drawable.background_common, R.drawable.background_rare,
            R.drawable.background_ultra, R.drawable.background_legendary, R.drawable.background_mythic};
    private int[] rarityColors = new int[]{R.color.common, R.color.rare, R.color.ultra,
            R.color.legendary, R.color.mythic};

    public ObjectModel(String objectImage, String objectName, int objectChance,
                       int chipPrice, int glazePrice, int timeBuff, int chipBuff, int glazeBuff) {
        this.objectImage = objectImage;
        this.objectName = objectName;
        this.objectChance = objectChance;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
        this.timeBuff = timeBuff;
        this.chipBuff = chipBuff;
        this.glazeBuff = glazeBuff;
    }

    public ObjectModel(int id, String objectImage, String objectName, int objectChance,
                       int chipPrice, int glazePrice, int timeBuff, int chipBuff, int glazeBuff) {
        this.id = id;
        this.objectImage = objectImage;
        this.objectName = objectName;
        this.objectChance = objectChance;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
        this.timeBuff = timeBuff;
        this.chipBuff = chipBuff;
        this.glazeBuff = glazeBuff;
    }

    public int rarityToInt() {
        int number = 0;

        if (getRarity().equals(rarities[0])) {
            number = 0;
        } else if (getRarity().equals(rarities[1])) {
            number = 1;
        } else if (getRarity().equals(rarities[2])) {
            number = 2;
        } else if (getRarity().equals(rarities[3])) {
            number = 3;
        } else if (getRarity().equals(rarities[4])) {
            number = 4;
        }

        return number;
    }

    public int getRarityColor() {
        return rarityColors[rarityToInt()];
    }

    public int getRarityBackground() {
        return rarityBackgrounds[rarityToInt()];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObjectImage() {
        return objectImage;
    }

    public void setObjectImage(String objectImage) {
        this.objectImage = objectImage;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getObjectChance() {
        return objectChance;
    }

    public void setObjectChance(int objectChance) {
        this.objectChance = objectChance;
    }

    public int getChipPrice() {
        return chipPrice;
    }

    public void setChipPrice(int chipPrice) {
        this.chipPrice = chipPrice;
    }

    public int getGlazePrice() {
        return glazePrice;
    }

    public void setGlazePrice(int glazePrice) {
        this.glazePrice = glazePrice;
    }

    public int getTimeBuff() {
        return timeBuff;
    }

    public void setTimeBuff(int timeBuff) {
        this.timeBuff = timeBuff;
    }

    public int getChipBuff() {
        return chipBuff;
    }

    public void setChipBuff(int chipBuff) {
        this.chipBuff = chipBuff;
    }

    public int getGlazeBuff() {
        return glazeBuff;
    }

    public void setGlazeBuff(int glazeBuff) {
        this.glazeBuff = glazeBuff;
    }
}
