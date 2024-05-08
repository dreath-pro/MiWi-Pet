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

public class EggSource {
    ArrayList<EggModel> eggLists = new ArrayList<>();

    public EggSource()
    {
        regenerateEgg();
    }

    private void regenerateEgg()
    {
        eggLists.clear();

        eggLists.add(new NormalEgg());
        eggLists.add(new ForestEgg());
        eggLists.add(new OceanEgg());
        eggLists.add(new RiverEgg());
        eggLists.add(new FossilEgg());
        eggLists.add(new ChristmasEgg());
        eggLists.add(new SavannaEgg());
        eggLists.add(new NostalgiaEgg());
    }

    public int getCount()
    {
        return eggLists.size();
    }

    public String getName(int index)
    {
        return eggLists.get(index).getEggName();
    }

    public EggModel getEgg(int index)
    {
        regenerateEgg();
        return eggLists.get(index);
    }
}
