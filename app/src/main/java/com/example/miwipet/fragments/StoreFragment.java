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
import android.widget.TextView;

import com.example.miwipet.R;
import com.example.miwipet.adapters.EggShopSelectionAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.FossilEgg;
import com.example.miwipet.models.eggs.NormalEgg;
import com.example.miwipet.models.eggs.OceanEgg;
import com.example.miwipet.models.eggs.RiverEgg;

import java.util.ArrayList;
import java.util.Random;

public class StoreFragment extends Fragment {
    private EggShopSelectionAdapter eggShopSelectionAdapter;
    private ArrayList<EggModel> eggModels = new ArrayList<>();
    private InventoryModel inventoryModels;
    private RecyclerView eggShopSelection, itemShopSelection, foodShopSelection;
    private Context context;

    private TextView chipToken, glazeToken;

    public StoreFragment(InventoryModel inventoryModels, TextView chipToken, TextView glazeToken) {
        this.inventoryModels = inventoryModels;
        this.chipToken = chipToken;
        this.glazeToken = glazeToken;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        eggShopSelection = view.findViewById(R.id.eggShopSelection);
        itemShopSelection = view.findViewById(R.id.itemShopSelection);
        foodShopSelection = view.findViewById(R.id.foodShopSelection);

        for (int i = 0; i <= 80; i++) {
            Random random = new Random();
            int selectedEgg = random.nextInt(6);

            switch (selectedEgg) {
                case 0:
                    eggModels.add(new NormalEgg());
                    break;
                case 1:
                    eggModels.add(new ForestEgg());
                    break;
                case 2:
                    eggModels.add(new OceanEgg());
                    break;
                case 3:
                    eggModels.add(new FossilEgg());
                    break;
                case 4:
                    eggModels.add(new ChristmasEgg());
                    break;
                case 5:
                    eggModels.add(new RiverEgg());
                    break;
            }

//            case 3:
//                eggModels.add(new FossilEgg());
//                break;
//            case 4:
//                eggModels.add(new ChristmasEgg());
//                break;
        }

        eggShopSelectionAdapter = new EggShopSelectionAdapter(context, eggModels, chipToken, glazeToken, inventoryModels);
        eggShopSelection.setAdapter(eggShopSelectionAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        eggShopSelection.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }
}