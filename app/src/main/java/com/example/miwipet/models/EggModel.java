package com.example.miwipet.models;

import java.util.ArrayList;
import java.util.Random;

public class EggModel{
    private String eggName;
    private int eggImage;
    private String petName;
    private int petImage;
    private int age, type;

    private int second, minute, hour;
    private boolean toHatch;
    private Random random = new Random();
    private String rarityText;

    public EggModel(String eggName, int eggImage, int second, int minute, int hour) {
        this.eggName = eggName;
        this.eggImage = eggImage;
        this.second = second;
        this.minute = minute;
        this.hour = hour;

        age = 0;
        type = 0;
        toHatch = false;
    }

    protected void selectRarity(int[][] petImage, String[][] petNames)
    {
        int selectedRarity = random.nextInt(100) + 1;
        int selectedPet;

        if(selectedRarity <= 2)
        {
            selectedPet = random.nextInt(petImage[4].length);
            this.petImage = petImage[4][selectedPet];
            this.petName = petNames[4][selectedPet];

            rarityText = "Mythic";
        }else if(selectedRarity <= 10)
        {
            selectedPet = random.nextInt(petImage[3].length);
            this.petImage = petImage[3][selectedPet];
            this.petName = petNames[3][selectedPet];

            rarityText = "Ancient";
        }else if(selectedRarity <= 25)
        {
            selectedPet = random.nextInt(petImage[2].length);
            this.petImage = petImage[2][selectedPet];
            this.petName = petNames[2][selectedPet];

            rarityText = "Rare";
        }else if(selectedRarity <= 60)
        {
            selectedPet = random.nextInt(petImage[1].length);
            this.petImage = petImage[1][selectedPet];
            this.petName = petNames[1][selectedPet];

            rarityText = "Uncommon";
        }else
        {
            selectedPet = random.nextInt(petImage[0].length);
            this.petImage = petImage[0][selectedPet];
            this.petName = petNames[0][selectedPet];

            rarityText = "Common";
        }
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public int getEggImage() {
        return eggImage;
    }

    public void setEggImage(int eggImage) {
        this.eggImage = eggImage;
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

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    public String getRarityText() {
        return rarityText;
    }

    public void setRarityText(String rarityText) {
        this.rarityText = rarityText;
    }

    public boolean isToHatch() {
        return toHatch;
    }

    public void setToHatch(boolean toHatch) {
        this.toHatch = toHatch;
    }
}
