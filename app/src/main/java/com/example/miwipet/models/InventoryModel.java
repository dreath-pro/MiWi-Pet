package com.example.miwipet.models;

import java.util.ArrayList;

public class InventoryModel {
    private ArrayList<PetModel> petLists = new ArrayList<>();
    private ArrayList<EggModel> eggLists = new ArrayList<>();
    private ArrayList<FoodModel> foodLists = new ArrayList<>();
    private ArrayList<ObjectModel> objectLists = new ArrayList<>();
    private int chipToken = 0, glazeToken = 0;

    public InventoryModel() {

    }

    public ArrayList<PetModel> getPetLists() {
        return petLists;
    }
    public void addPetLists(PetModel pet) {
        petLists.add(pet);
    }
    public void removePetLists(int idx) {
        petLists.remove(idx);
    }
    public void clearPetLists() {
        petLists.clear();
    }


    public ArrayList<EggModel> getEggLists() {
        return eggLists;
    }
    public void addEggLists(EggModel egg) {
        eggLists.add(egg);
    }
    public void removeEggLists(int idx) {
        eggLists.remove(idx);
    }
    public void clearEggList() {
        eggLists.clear();
    }


    public ArrayList<FoodModel> getFoodLists() {
        return foodLists;
    }
    public void addFoodLists(FoodModel food) {
        foodLists.add(food);
    }
    public void removeFoodLists(int idx) {
        foodLists.remove(idx);
    }
    public void clearFoodList() {
        foodLists.clear();
    }


    public ArrayList<ObjectModel> getObjectLists() {
        return objectLists;
    }
    public void addObjectLists(ObjectModel object) {
        objectLists.add(object);
    }
    public void removeObjectLists(int idx) {
        objectLists.remove(idx);
    }
    public void clearObjectList() {
        objectLists.clear();
    }


    public int getChipToken() {
        return chipToken;
    }
    public void setChipToken(int chipToken) {
        this.chipToken = chipToken;
    }
    public int getGlazeToken() {
        return glazeToken;
    }
    public void setGlazeToken(int glazeToken) {
        this.glazeToken = glazeToken;
    }
}
