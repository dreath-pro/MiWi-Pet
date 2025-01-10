package com.example.miwipet.models;

import com.example.miwipet.utils.Rarity;

import java.util.Random;

public class EggModel {
    private int id;
    private String eggName;
    private String eggImage;
    private String petName;
    private String petImage;
    private int age, type;
    private int chipPrice, glazePrice;

    private int second, minute, hour;
    private int eggPercentage;
    private boolean toHatch, isSelected;
    private Random random = new Random();
    private String rarityText;
    private Rarity rarity = new Rarity();

    private String[][] petNames;
    private String[][] petImages;

    public EggModel(String eggName) {
        this.eggName = eggName;
    }

    public EggModel(int id, String eggName, String eggImage, String petName, String petImage, int age, int type,
                    int chipPrice, int glazePrice, int second, int minute, int hour, boolean toHatch,
                    boolean isSelected, String rarityText, int eggPercentage) {
        this.id = id;
        this.eggName = eggName;
        this.eggImage = eggImage;
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.toHatch = toHatch;
        this.isSelected = isSelected;
        this.rarityText = rarityText;
        this.eggPercentage = eggPercentage;
    }

    public EggModel(String eggName, String eggImage, int chipPrice, int glazePrice, int second, int minute, int hour, int eggPercentage) {
        this.eggName = eggName;
        this.eggImage = eggImage;
        this.chipPrice = chipPrice;
        this.glazePrice = glazePrice;
        this.second = second;
        this.minute = minute;
        this.hour = hour;
        this.eggPercentage = eggPercentage;

        age = 0;
        selectType();
        toHatch = false;
        isSelected = false;
    }

    protected void selectRarity(String[][] petImages, String[][] petNames) {
        this.petImages = petImages;
        this.petNames = petNames;

        selectPet();
    }

    public void repickEgg() {
        selectType();
        selectPet();
    }

    private void selectType() {
        type = 0;
        int selectedType = random.nextInt(3);
        switch (selectedType) {
            case 0:
                type = 0;
                break;
            case 1:
                type = 1;
                break;
            case 2:
                type = 2;
                break;
        }
    }

    private void selectPet() {
        int selectedRarity = random.nextInt(100) + 1;
        int selectedPet;

        if (selectedRarity <= 2) {
            selectedPet = random.nextInt(petImages[4].length);
            this.petImage = petImages[4][selectedPet];
            this.petName = petNames[4][selectedPet];

            rarityText = rarity.getRarity(4);
        } else if (selectedRarity <= 10) {
            selectedPet = random.nextInt(petImages[3].length);
            this.petImage = petImages[3][selectedPet];
            this.petName = petNames[3][selectedPet];

            rarityText = rarity.getRarity(3);
        } else if (selectedRarity <= 25) {
            selectedPet = random.nextInt(petImages[2].length);
            this.petImage = petImages[2][selectedPet];
            this.petName = petNames[2][selectedPet];

            rarityText = rarity.getRarity(2);
        } else if (selectedRarity <= 60) {
            selectedPet = random.nextInt(petImages[1].length);
            this.petImage = petImages[1][selectedPet];
            this.petName = petNames[1][selectedPet];

            rarityText = rarity.getRarity(1);
        } else {
            selectedPet = random.nextInt(petImages[0].length);
            this.petImage = petImages[0][selectedPet];
            this.petName = petNames[0][selectedPet];

            rarityText = rarity.getRarity(0);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEggName() {
        return eggName;
    }

    public void setEggName(String eggName) {
        this.eggName = eggName;
    }

    public String getEggImage() {
        return eggImage;
    }

    public void setEggImage(String eggImage) {
        this.eggImage = eggImage;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetImage() {
        return petImage;
    }

    public void setPetImage(String petImage) {
        this.petImage = petImage;
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

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getEggPercentage() {
        return eggPercentage;
    }

    public void setEggPercentage(int eggPercentage) {
        this.eggPercentage = eggPercentage;
    }
}
