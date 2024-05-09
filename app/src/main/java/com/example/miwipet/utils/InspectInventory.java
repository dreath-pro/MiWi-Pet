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
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.guardian_deer);
                inventoryModel.getPetLists().get(count).setPetName("Guardian Deer");
            }

            if(petModel.getPetName().equals("Vampire Bat"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.dark_deer);
                inventoryModel.getPetLists().get(count).setPetName("Dark Deer");
            }

            if(petModel.getPetName().equals("Kangaroo") && petModel.getRarity().equals(rarity.getRarity(2)))
            {
                inventoryModel.getPetLists().get(count).setRarity(rarity.getRarity(3));
            }

            if(petModel.getPetName().equals("Tree Deer"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.guardian_deer);
                inventoryModel.getPetLists().get(count).setPetName("Guardian Deer");
            }

            if(petModel.getPetName().equals("Soul Dragon"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.dark_deer);
                inventoryModel.getPetLists().get(count).setPetName("Dark Deer");
            }

            if(petModel.getPetName().equals("Carnotaurus"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.phorusrhacids);
                inventoryModel.getPetLists().get(count).setPetName("Phorusrhacids");
            }

            if(petModel.getPetName().equals("Buffalo"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.musk_ox);
                inventoryModel.getPetLists().get(count).setPetName("Musk Ox");
            }

            if(petModel.getPetName().equals("Musk Ox"))
            {
                inventoryModel.getPetLists().get(count).setPetImage(R.drawable.buffalo);
                inventoryModel.getPetLists().get(count).setPetName("Buffalo");
            }

            count++;
        }
    }

    public void updatePetImage()
    {
        int count = 0;

        for(PetModel petModel : inventoryModel.getPetLists())
        {
            for(int i = 0; i <= eggSource.getCount() - 1; i++)
            {
                if(eggSource.getEgg(i).isNameExist(petModel.getPetName()))
                {
                    inventoryModel.getPetLists().get(count).setPetImage(eggSource.getEgg(i).refreshPetImage(petModel.getPetName()));
                }
            }

            count++;
        }
    }

    public void updateEggPetImage()
    {
        int count = 0;

        for(EggModel eggModel : inventoryModel.getEggLists())
        {
            for(int i = 0; i <= eggSource.getCount() - 1; i++)
            {
                if(eggSource.getEgg(i).isNameExist(eggModel.getPetName()))
                {
                    inventoryModel.getEggLists().get(count).setPetImage(eggSource.getEgg(i).refreshPetImage(eggModel.getPetName()));
                }
            }
        }

        count++;
    }
}
