package com.example.miwipet.models;

import com.example.miwipet.R;
import com.example.miwipet.utils.Rarity;

public class FoodModel {
    private int id;
    private String foodImage;
    private String foodName;
    private String rarity;
    private int expReward;
    private int foodPercentage;
    private int chipPrice, glazePrice;

    private Rarity rarityClass = new Rarity();
    private String[] rarities = new String[]{
            rarityClass.getRarity(0),
            rarityClass.getRarity(1),
            rarityClass.getRarity(2),
            rarityClass.getRarity(3),
            rarityClass.getRarity(4)};

    private int[] rarityBackgrounds = new int[]{R.drawable.background_common, R.drawable.background_rare,
            R.drawable.background_ultra, R.drawable.background_legendary, R.drawable.background_mythic};
    private int[] rarityColors = new int[]{R.color.common, R.color.rare, R.color.ultra,
            R.color.legendary, R.color.mythic};

    public FoodModel(String foodImage, String foodName, int expReward, int foodPercentage, int chipPrice, int glazePrice) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.expReward = expReward;
        this.foodPercentage = foodPercentage;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
    }

    public FoodModel(int id, String foodImage, String foodName, int expReward, int foodPercentage, int chipPrice, int glazePrice) {
        this.id = id;
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.expReward = expReward;
        this.foodPercentage = foodPercentage;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
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

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public int getExpReward() {
        return expReward;
    }

    public void setExpReward(int expReward) {
        this.expReward = expReward;
    }

    public int getFoodPercentage() {
        return foodPercentage;
    }

    public void setFoodPercentage(int foodPercentage) {
        this.foodPercentage = foodPercentage;
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
}
