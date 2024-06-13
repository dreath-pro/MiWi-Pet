package com.example.miwipet.models;

import com.example.miwipet.R;
import com.example.miwipet.utils.Rarity;

public class PetModel {
    private String petName, rarity, petImage;
    private int age, type;
    private int maxExp;
    private int exp;

    private Rarity rarityClass = new Rarity();
    private String[] rarities = new String[]{
            rarityClass.getRarity(0),
            rarityClass.getRarity(1),
            rarityClass.getRarity(2),
            rarityClass.getRarity(3),
            rarityClass.getRarity(4)};

    private int[] rarityBackgrounds = new int[]{R.drawable.background_common, R.drawable.background_rare,
            R.drawable.background_ultra, R.drawable.background_legendary, R.drawable.background_mythic};
    private int[] typeBackgrounds = new int[]{R.drawable.background_normal, R.drawable.background_crystal, R.drawable.background_gemstone};

    private int[] rarityColors = new int[]{R.color.common, R.color.rare, R.color.ultra,
            R.color.legendary, R.color.mythic};
    private int[] typeColors = new int[]{R.color.normal, R.color.crystal, R.color.gemstone};

    public PetModel(String petName)
    {
        this.petName = petName;
    }

    public PetModel(String petName, String petImage, int age, int type, String rarity) {
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
        this.rarity = rarity;

        maxExp = 50;
        exp = 0;
    }

    public PetModel(String petName, String petImage, int age, int type, String rarity, int maxExp, int exp) {
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
        this.rarity = rarity;
        this.maxExp = maxExp;
        this.exp = exp;
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

    public String typeToString() {
        String text = "";

        switch (getType()) {
            case 0:
                text = "Normal";
                break;
            case 1:
                text = "Crystal";
                break;
            case 2:
                text = "Gemstone";
                break;
        }

        return text;
    }

    public String ageToString() {
        String text = "";

        switch (getAge()) {
            case 0:
                text = "Offspring";
                break;
            case 1:
                text = "Baby";
                break;
            case 2:
                text = "Teen";
                break;
            case 3:
                text = "Adult";
                break;
        }

        return text;
    }

    private void growAge() {
        /*
        max exp
        offspring = 50
        baby = 100
        teen = 200
        adult = 400
         */

        if (age <= 3) {
            if (exp >= maxExp) {
                age += 1;
                exp -= maxExp;
                maxExp *= 2;

                while (exp >= maxExp) {
                    age += 1;
                    exp -= maxExp;
                    maxExp *= 2;
                }
            }
        }

        if(age == 3)
        {
            exp = maxExp;
        }
    }

    public int getRarityColor() {
        return rarityColors[rarityToInt()];
    }

    public int getRarityBackground()
    {
        return rarityBackgrounds[rarityToInt()];
    }

    public int getTypeColor() {
        return typeColors[getType()];
    }

    public int getTypeBackground()
    {
        return typeBackgrounds[getType()];
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
