package com.example.miwipet.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miwipet.R;
import com.example.miwipet.adapters.InventoryAdapter;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class CollectionFragment extends Fragment {
    RecyclerView petInventory;
    Context context;

    private ArrayList<PetModel> petModels;

    public CollectionFragment(ArrayList<PetModel> petModels, Context context)
    {
        this.petModels = petModels;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        petInventory = view.findViewById(R.id.petInventory);

        InventoryAdapter inventoryAdapter = new InventoryAdapter(context, petModels);
        petInventory.setAdapter(inventoryAdapter);
        petInventory.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }
}