package com.example.miwipet.fragments.navigation;

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
import com.example.miwipet.utils.EggSource;

import java.util.ArrayList;
import java.util.Random;

public class StoreFragment extends Fragment {
    private EggShopSelectionAdapter eggShopSelectionAdapter;
    private ArrayList<EggModel> eggDisplay = new ArrayList<>();
    private InventoryModel inventoryModels;
    private RecyclerView eggShopSelection, itemShopSelection, foodShopSelection;
    private Context context;

    private EggSource eggSource = new EggSource();

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

        eggDisplay.addAll(eggSource.fetchStoreList());

        eggShopSelectionAdapter = new EggShopSelectionAdapter(context, eggSource, eggDisplay, chipToken, glazeToken, inventoryModels);
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