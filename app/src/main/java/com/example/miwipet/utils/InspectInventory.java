package com.example.miwipet.utils;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InspectInventory {
    private InventoryModel inventoryModel;
    private Rarity rarity = new Rarity();
    private EggSource eggSource = new EggSource();

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

            if(petModel.getPetName().equals("Vampire Bat"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("dark_deer");
                inventoryModel.getPetLists().get(count).setPetName("Dark Deer");
            }

            if(petModel.getPetName().equals("Kangaroo") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(3));
            }

            if(petModel.getPetName().equals("Bee") && petModel.getRarity().equals(rarity.getRarity(1)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(0));
            }

            if(petModel.getPetName().equals("Grasshopper") && petModel.getRarity().equals(rarity.getRarity(1)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(0));
            }

            if(petModel.getPetName().equals("Butterfly") && petModel.getRarity().equals(rarity.getRarity(1)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(0));
            }

            if(petModel.getPetName().equals("Bird") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));
            }

            if(petModel.getPetName().equals("Sheep") && petModel.getRarity().equals(rarity.getRarity(3)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(2));
            }

            if(petModel.getPetName().equals("Chicken") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));
            }

            if(petModel.getPetName().equals("Blue Chick") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));
            }

            if(petModel.getPetName().equals("Lobster") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(1));
            }

            if(petModel.getPetName().equals("Lion") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(3));
            }

            if(petModel.getPetName().equals("Tree Deer"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("dire_wolf");
                inventoryModel.getPetLists().get(count).setPetName("Dire Wolf");
            }

            if(petModel.getPetName().equals("Blazing Lion"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("sun_war_lion");
                inventoryModel.getPetLists().get(count).setPetName("Sun War Lion");
            }

            if(petModel.getPetName().equals("Guardian Deer"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("dire_wolf");
                inventoryModel.getPetLists().get(count).setPetName("Dire Wolf");
            }

            if(petModel.getPetName().equals("Soul Dragon"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("dark_deer");
                inventoryModel.getPetLists().get(count).setPetName("Dark Deer");
            }

            if(petModel.getPetName().equals("Carnotaurus"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("phorusrhacids");
                inventoryModel.getPetLists().get(count).setPetName("Phorusrhacids");
            }

            if(petModel.getPetName().equals("Buffalo"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("boar");
                inventoryModel.getPetLists().get(count).setPetName("Boar");
            }

            if(petModel.getPetName().equals("Musk Ox"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("boar");
                inventoryModel.getPetLists().get(count).setPetName("Boar");
            }

//            if(!petImage.isEmpty())
//            {
//                if(petModel.getPetImage().equals("mecha_spermwhale"))
//                {
//                    inventoryModel.getPetLists().get(count).setPetImage("mecha_sperm_whale");
//                }
//            }

            if(petModel.getPetName().equals("Goldfish"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("mecha_sperm_whale");
                inventoryModel.getPetLists().get(count).setPetName("Mecha Sperm Whale");
            }

            if(petModel.getPetName().equals("Ghost Shark"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("terror_shark");
                inventoryModel.getPetLists().get(count).setPetName("Terror Shark");
            }

            if(petModel.getPetName().equals("Hollow Squid"))
            {
                inventoryModel.getPetLists().get(count).setPetImage("devil_kraken");
                inventoryModel.getPetLists().get(count).setPetName("Devil Kraken");
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
}
