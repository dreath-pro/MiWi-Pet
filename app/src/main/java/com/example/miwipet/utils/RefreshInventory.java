package com.example.miwipet.utils;

import android.content.Context;

import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.database.FoodDatabase;
import com.example.miwipet.database.ObjectDatabase;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class RefreshInventory {
    private PetDatabase petDatabase;
    private EggDatabase eggDatabase;
    private FoodDatabase foodDatabase;
    private ObjectDatabase objectDatabase;
    private InventoryModel inventoryModel;

    public RefreshInventory(Context context, InventoryModel inventoryModel) {
        this.inventoryModel = inventoryModel;
        petDatabase = new PetDatabase(context);
        eggDatabase = new EggDatabase(context);
        foodDatabase = new FoodDatabase(context);
        objectDatabase = new ObjectDatabase(context);
    }

    public void getPetFromDatabase() {
        ArrayList<PetModel> petModels;
        petModels = petDatabase.getPetList();

        inventoryModel.clearPetLists();
        for (PetModel pet : petModels) {
            inventoryModel.addPetLists(pet);
        }
    }

    public void getEggFromDatabase() {
        ArrayList<EggModel> eggModels;
        eggModels = eggDatabase.getEggList();

        inventoryModel.clearEggList();
        for (EggModel egg : eggModels) {
            inventoryModel.addEggLists(egg);
        }
    }

    public void getFoodFromDatabase() {
        ArrayList<FoodModel> foodModels;
        foodModels = foodDatabase.getFoodList();

        inventoryModel.clearFoodList();
        for (FoodModel food : foodModels) {
            inventoryModel.addFoodLists(food);
        }
    }

    public void getObjectFromDatabase()
    {
        ArrayList<ObjectModel> objectModels;
        objectModels = objectDatabase.getObjectList();

        inventoryModel.clearObjectList();
        for(ObjectModel object : objectModels)
        {
            inventoryModel.addObjectLists(object);
        }
    }
}
