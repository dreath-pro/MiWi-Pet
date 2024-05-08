package com.example.miwipet.utils;

import android.content.Context;

import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class RefreshInventory {
    private PetDatabase petDatabase;
    private EggDatabase eggDatabase;
    private InventoryModel inventoryModel;

    public RefreshInventory(Context context, InventoryModel inventoryModel)
    {
        this.inventoryModel = inventoryModel;
        petDatabase = new PetDatabase(context);
        eggDatabase = new EggDatabase(context);
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
}
