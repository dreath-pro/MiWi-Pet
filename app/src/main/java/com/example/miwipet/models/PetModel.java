package com.example.miwipet.models;

public class PetModel {
    private String petName;
    private int petImage, age, type;

    public PetModel(String petName, int petImage, int age, int type) {
        this.petName = petName;
        this.petImage = petImage;
        this.age = age;
        this.type = type;
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
}
