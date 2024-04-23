package com.example.miwipet.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miwipet.R;
import com.example.miwipet.adapters.EggShopSelectionAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.FossilEgg;
import com.example.miwipet.models.eggs.OceanEgg;

import java.util.ArrayList;

public class StoreFragment extends Fragment {
    private EggShopSelectionAdapter eggShopSelectionAdapter;
    private ArrayList<EggModel> eggModels = new ArrayList<>();
    private RecyclerView eggShopSelection, itemShopSelection, foodShopSelection;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        eggShopSelection = view.findViewById(R.id.eggShopSelection);
        itemShopSelection = view.findViewById(R.id.itemShopSelection);
        foodShopSelection = view.findViewById(R.id.foodShopSelection);

        eggModels.add(new ChristmasEgg());
        eggModels.add(new FossilEgg());
        eggModels.add(new OceanEgg());
        eggModels.add(new ForestEgg());
        eggModels.add(new OceanEgg());
        eggModels.add(new OceanEgg());
        eggModels.add(new ChristmasEgg());
        eggModels.add(new ForestEgg());
        eggModels.add(new ForestEgg());

        eggShopSelectionAdapter = new EggShopSelectionAdapter(context, eggModels);
        eggShopSelection.setAdapter(eggShopSelectionAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        eggShopSelection.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        this.context = context;
    }
}