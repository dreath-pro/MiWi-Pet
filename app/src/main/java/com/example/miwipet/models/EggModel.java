package com.example.miwipet.models;

import java.util.ArrayList;
import java.util.Random;

public class EggModel{
    private String eggName;
    private int[][] petImage;
    private int eggImage;
    private int second, minute, hour;
    private int petBirth;

    private Random random = new Random();
    private int selectedPet;
    private String rarityText;

    public EggModel(String eggName, int eggImage, int second, int minute, int hour) {
        this.eggName = eggName;
        this.eggImage = eggImage;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
    }

    protected void selectRarity(int[][] petImage)
    {
        this.petImage = petImage;
        int selectedRarity = random.nextInt(100) + 1;

        if(selectedRarity <= 2)
        {
            selectedPet = random.nextInt(petImage[4].length);
            petBirth = petImage[4][selectedPet];

            rarityText = "Mythic";
        }else if(selectedRarity <= 10)
        {
            selectedPet = random.nextInt(petImage[3].length);
            petBirth = petImage[3][selectedPet];

            rarityText = "Ancient";
        }else if(selectedRarity <= 25)
        {
            selectedPet = random.nextInt(petImage[2].length);
            petBirth = petImage[2][selectedPet];

            rarityText = "Rare";
        }else if(selectedRarity <= 60)
        {
            selectedPet = random.nextInt(petImage[1].length);
            petBirth = petImage[1][selectedPet];

            rarityText = "Uncommon";
        }else
        {
            selectedPet = random.nextInt(petImage[0].length);
            petBirth = petImage[0][selectedPet];

            rarityText = "Common";
        }
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public int[][] getPetImage() {
        return petImage;
    }

    public void setPetImage(int[][] petImage) {
        this.petImage = petImage;
    }

    public int getEggImage() {
        return eggImage;
    }

    public void setEggImage(int eggImage) {
        this.eggImage = eggImage;
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

    public int getSelectedPet() {
        return selectedPet;
    }

    public void setSelectedPet(int selectedPet) {
        this.selectedPet = selectedPet;
    }

    public int getPetBirth() {
        return petBirth;
    }

    public void setPetBirth(int petBirth) {
        this.petBirth = petBirth;
    }

    public String getRarityText() {
        return rarityText;
    }

    public void setRarityText(String rarityText) {
        this.rarityText = rarityText;
    }
}
