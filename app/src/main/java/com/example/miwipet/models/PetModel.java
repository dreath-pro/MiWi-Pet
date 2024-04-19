package com.example.miwipet.models;

public class PetModel {
    /**
     * age 0 = newborn
     * age 1 = juvenile
     * age 2 = teen
     * age 3 = young adult
     * age 4 = adult
     * age 5 = elder
     */

    private String petName, rarity;
    private int petImage, age, type;
    private int maxExp;
    private int exp;

    public PetModel(String petName, int petImage, int age, int type, String rarity) {
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
        this.rarity = rarity;

        maxExp = 50;
        exp = 0;
    }

    public PetModel(String petName, int petImage, int age, int type, String rarity, int maxExp, int exp)
    {
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
        this.rarity = rarity;
        this.maxExp = maxExp;
        this.exp = exp;
    }

    private void growAge() {
        if(exp >= maxExp)
        {
            age += 1;
            exp -= maxExp;
            maxExp *= 2;

            while(exp >= maxExp)
            {
                age += 1;
                exp -= maxExp;
                maxExp *= 2;
            }
        }
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetImage() {
        return petImage;
    }

    public void setPetImage(int petImage) {
        this.petImage = petImage;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getMaxExp() {
        return maxExp;
    }

    public void setMaxExp(int maxExp) {
        this.maxExp = maxExp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp += exp;
        growAge();
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
