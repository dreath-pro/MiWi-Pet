package com.example.miwipet.utils;

import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.FossilEgg;
import com.example.miwipet.models.eggs.NormalEgg;
import com.example.miwipet.models.eggs.NostalgiaEgg;
import com.example.miwipet.models.eggs.OceanEgg;
import com.example.miwipet.models.eggs.RiverEgg;
import com.example.miwipet.models.eggs.SavannaEgg;

import java.util.ArrayList;
import java.util.Random;

public class EggSource {
    private ArrayList<EggModel> eggLists = new ArrayList<>();

    public EggSource()
    {

    }

    private void generateEggs(String type)
    {
        eggLists.clear();

        switch (type)
        {
            case "All":
                eggLists.add(new NormalEgg());
                eggLists.add(new ForestEgg());
                eggLists.add(new OceanEgg());
                eggLists.add(new RiverEgg());
                eggLists.add(new SavannaEgg());
                eggLists.add(new NostalgiaEgg());
                eggLists.add(new ChristmasEgg());
                eggLists.add(new FossilEgg());
                break;
            case "Store":
                eggLists.add(new NormalEgg());
                eggLists.add(new ForestEgg());
                eggLists.add(new OceanEgg());
                eggLists.add(new RiverEgg());
                eggLists.add(new SavannaEgg());
                eggLists.add(new NostalgiaEgg());
                break;
            case "Christmas":
                eggLists.add(new ChristmasEgg());
                break;
            case "Fossil":
                eggLists.add(new FossilEgg());
                break;
        }
    }

    public ArrayList<EggModel> getStoreEggs()
    {
        generateEggs("Store");

        ArrayList<EggModel> selectedEggsList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i <= eggLists.size() - 1; i++)
        {
            int chances = random.nextInt(100) + 1;

            if(chances <= eggLists.get(i).getEggPercentage())
            {
                selectedEggsList.add(eggLists.get(i));
            }
        }

        eggLists.clear();
        eggLists.addAll(selectedEggsList);

        return eggLists;
    }

    public int getCount()
    {
        return eggLists.size();
    }

    public ArrayList<EggModel> getEggsByString(ArrayList<String> eggNames)
    {
        ArrayList<EggModel> returnedEggList = new ArrayList<>();

        generateEggs("All");

        for(String eggName : eggNames)
        {
            for(EggModel eggList : eggLists)
            {
                if(eggName.equals(eggList.getEggName()))
                {
                    returnedEggList.add(eggList);
                }
            }
        }

        return returnedEggList;
    }

    public EggModel getEggByString(String eggName)
    {
        EggModel returnedEgg = null;

        generateEggs("All");

        for(EggModel egglist : eggLists)
        {
            if(eggName.equals(egglist.getEggName()))
            {
                returnedEgg = egglist;
            }
        }

        return returnedEgg;
    }
}
