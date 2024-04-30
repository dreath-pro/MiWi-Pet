package com.example.miwipet.utils;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InspectInventory {
    private InventoryModel inventoryModel;
    private Rarity rarity = new Rarity();
    private boolean withResult = false;

    public InspectInventory(InventoryModel inventoryModel)
    {
        this.inventoryModel = inventoryModel;
    }

    public InventoryModel updatePet()
    {
        int count = 0;

        for(PetModel petModel : inventoryModel.getPetLists())
        {
            if(petModel.getPetName().equals("Bat") && petModel.getPetName().equals(rarity.getRarity(0)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));

                setWithResult(true);
            }

            if(petModel.getPetName().equals("Golden Tiger"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.tree_deer);
                inventoryModel.getPetLists().get(count).setPetName("Tree Deer");

                setWithResult(true);
            }

            if(petModel.getPetName().equals("Vampire Bat"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.soul_dragon);
                inventoryModel.getPetLists().get(count).setPetName("Soul Dragon");

                setWithResult(true);
            }

            count++;
        }

        return inventoryModel;
    }

    public boolean isWithResult() {
        return withResult;
    }

    public void setWithResult(boolean withResult) {
        this.withResult = withResult;
    }
}
