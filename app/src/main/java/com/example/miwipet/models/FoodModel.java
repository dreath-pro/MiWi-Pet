package com.example.miwipet.models;

public class FoodModel {
    private int id;
    private int foodImage;
    private String foodName;
    private String rarity;
    private int expReward;
    private int foodPercentage;
    private int chipPrice, glazePrice;

    public FoodModel()
    {

    }

    public FoodModel(int foodImage, String foodName, int expReward, int foodPercentage, int chipPrice, int glazePrice) {
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.expReward = expReward;
        this.foodPercentage = foodPercentage;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
    }

    public FoodModel(int id, int foodImage, String foodName, int expReward, int foodPercentage, int chipPrice, int glazePrice) {
        this.id = id;
        this.foodImage = foodImage;
        this.foodName = foodName;
        this.expReward = expReward;
        this.foodPercentage = foodPercentage;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
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
