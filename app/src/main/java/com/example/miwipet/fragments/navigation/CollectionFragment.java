package com.example.miwipet.fragments.navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miwipet.R;
import com.example.miwipet.adapters.FoodAdapter;
import com.example.miwipet.adapters.PetAdapter;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class CollectionFragment extends Fragment {
    private RecyclerView collectionView;
    private Context context;
    private Button petsButton, itemsButton, foodsButton;

    private InventoryModel inventoryModel;

    public CollectionFragment(InventoryModel inventoryModel, Context context)
    {
        this.inventoryModel = inventoryModel;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_collection, container, false);

        petsButton = view.findViewById(R.id.petsButton);
        itemsButton = view.findViewById(R.id.itemsButton);
        foodsButton = view.findViewById(R.id.foodsButton);
        collectionView = view.findViewById(R.id.collectionView);

        GridLayoutManager petLayoutManager = new GridLayoutManager(context, 2);
        GridLayoutManager foodLayoutManager = new GridLayoutManager(context, 2);
        showPetCollection(petLayoutManager);

        petsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPetCollection(petLayoutManager);
            }
        });

        itemsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodCollection(foodLayoutManager);
            }
        });

        return view;
    }

    private void showPetCollection(GridLayoutManager petLayoutManager)
    {
        collectionView.setLayoutManager(petLayoutManager);
        PetAdapter petAdapter = new PetAdapter(getActivity(), inventoryModel.getPetLists());
        collectionView.setAdapter(petAdapter);
    }

    private void showFoodCollection(GridLayoutManager foodLayoutManager)
    {
        collectionView.setLayoutManager(foodLayoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(), inventoryModel.getFoodLists());
        collectionView.setAdapter(foodAdapter);
    }
}