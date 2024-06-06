package com.example.miwipet.utils;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InspectInventory {
    private InventoryModel inventoryModel;
    private Rarity rarity = new Rarity();

    public InspectInventory(InventoryModel inventoryModel)
    {
        this.inventoryModel = inventoryModel;
    }

    public void updatePet()
    {
        int count = 0;

        for(PetModel petModel : inventoryModel.getPetLists())
        {
            if(petModel.getPetName().equals("Bat") && petModel.getRarity().equals(rarity.getRarity(0)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));
            }

            if(petModel.getPetName().equals("Golden Tiger"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("dire_wolf");
                inventoryModel.getPetLists().get(count).setPetName("Dire Wolf");
            }

            if(petModel.getPetName().equals("Lion") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(3));
            }

            count++;
        }
    }

    public void updatePetImage()
    {
        ArrayList<PetModel> petModels = new ArrayList<>();

        for(PetModel petModel : inventoryModel.getPetLists())
        {
            String lowerCaseName = petModel.getPetName().toLowerCase();
            String underscoreName = lowerCaseName.replace(" ", "_");
            underscoreName = underscoreName.replace("-", "_");

            petModel.setPetImage(underscoreName);
            petModels.add(petModel);
        }

        inventoryModel.clearPetLists();
        for(PetModel petList : petModels)
        {
            inventoryModel.addPetLists(petList);
        }
    }

    public void updateEggImage()
    {
        ArrayList<EggModel> eggModels = new ArrayList<>();

        for(EggModel eggModel : inventoryModel.getEggLists())
        {
            String lowerCaseName = eggModel.getEggName().toLowerCase();
            String removedEggLast = lowerCaseName.substring(0, eggModel.getEggName().length() - 4);
            String eggFrontName = "egg_" + removedEggLast;

            eggModel.setEggImage(eggFrontName);

            String lowerCaseName2 = eggModel.getPetName().toLowerCase();
            String underscoreName2 = lowerCaseName2.replace(" ", "_");
            underscoreName2 = underscoreName2.replace("-", "_");

            eggModel.setPetImage(underscoreName2);
            eggModels.add(eggModel);
        }

        inventoryModel.clearEggList();
        for(EggModel eggList : eggModels)
        {
            inventoryModel.addEggLists(eggList);
        }
    }

    public void updateFoodImage()
    {
        ArrayList<FoodModel> foodModels = new ArrayList<>();

        for(FoodModel foodModel : inventoryModel.getFoodLists())
        {
            String lowerCaseName = foodModel.getFoodName().toLowerCase();
            String underscoreName = lowerCaseName.replace(" ", "_");
            underscoreName = underscoreName.replace("-", "_");
            String foodFrontName = "food_" + underscoreName;

            foodModel.setFoodImage(foodFrontName);

            foodModels.add(foodModel);
        }

        inventoryModel.clearFoodList();
        for(FoodModel foodList : foodModels)
        {
            inventoryModel.addFoodLists(foodList);
        }
    }

    public void updateObjectImage()
    {
        ArrayList<ObjectModel> objectModels = new ArrayList<>();

        for(ObjectModel objectModel : inventoryModel.getObjectLists())
        {
            String lowerCaseName = objectModel.getObjectName().toLowerCase();
            String underscoreName = lowerCaseName.replace(" ", "_");
            underscoreName = underscoreName.replace("-", "_");
            String objectFrontName = "object_" + underscoreName;

            objectModel.setObjectImage(objectFrontName);

            objectModels.add(objectModel);
        }

        inventoryModel.clearObjectList();
        for(ObjectModel objectList : objectModels)
        {
            inventoryModel.addObjectLists(objectList);
        }
    }
}
