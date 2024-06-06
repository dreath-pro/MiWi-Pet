package com.example.miwipet.fragments.navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miwipet.R;
import com.example.miwipet.adapters.FoodAdapter;
import com.example.miwipet.adapters.ObjectAdapter;
import com.example.miwipet.adapters.PetAdapter;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;
import java.util.Optional;

public class CollectionFragment extends Fragment {
    private RecyclerView collectionView;
    private Context context;
    private Button petsButton, objectsButton, foodsButton;
    private EditText inventorySearch;
    private ImageView backPageButton, nextPageButton;
    private TextView pageIndicator;

    private int page = 1;
    private int selectedItem = 0;

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
        objectsButton = view.findViewById(R.id.objectsButton);
        foodsButton = view.findViewById(R.id.foodsButton);
        collectionView = view.findViewById(R.id.collectionView);
        inventorySearch = view.findViewById(R.id.inventorySearch);
        backPageButton = view.findViewById(R.id.backPageButton);
        nextPageButton = view.findViewById(R.id.nextPageButton);
        pageIndicator = view.findViewById(R.id.pageIndicator);

        showPetCollection(false, "");

        petsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPetCollection(false, "");
            }
        });

        objectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showObjectCollection(false, "");
            }
        });

        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFoodCollection(false, "");
            }
        });

        backPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                if (page < 1) {
                    page = 1;
                } else {
                    //resetAdapter();
                }

                pageIndicator.setText(page + " out of 10");
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                if (page > 10) {
                    page = 10;
                } else {
                    //resetAdapter();
                }

                pageIndicator.setText(page + " out of 10");
            }
        });

        inventorySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterInventory(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    private void filterInventory(String query)
    {
        /**
         * items
         * 0 - pets
         * 1 - foods
         * 2 - objects
         */
        if(!query.isEmpty())
        {
            switch (selectedItem)
            {
                case 0:
                    showPetCollection(true, query);
                    break;
                case 1:
                    showFoodCollection(true, query);
                    break;
                case 2:
                    showObjectCollection(true, query);
                    break;
            }
        }else
        {
            switch (selectedItem)
            {
                case 0:
                    showPetCollection(false, "");
                    break;
                case 1:
                    showFoodCollection(false, "");
                    break;
                case 2:
                    showObjectCollection(false, "");
                    break;
            }
        }
    }

    private void showPetCollection(boolean filtered, String query)
    {
        selectedItem = 0;
        ArrayList<PetModel> petLists = new ArrayList<>();

        if(!filtered)
        {
            petLists = inventoryModel.getPetLists();
        }else
        {
            for(PetModel pet : inventoryModel.getPetLists())
            {
                if(pet.getPetName().toLowerCase().contains(query.toLowerCase()))
                {
                    petLists.add(pet);
                }
            }
        }

        GridLayoutManager petLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(petLayoutManager);
        PetAdapter petAdapter = new PetAdapter(getActivity(), petLists);
        collectionView.setAdapter(petAdapter);
    }

    private void showFoodCollection(boolean filtered, String query)
    {
        selectedItem = 1;
        ArrayList<FoodModel> foodLists = new ArrayList<>();

        if(!filtered)
        {
            foodLists = inventoryModel.getFoodLists();
        }else
        {
            for(FoodModel food : inventoryModel.getFoodLists())
            {
                if(food.getFoodName().toLowerCase().contains(query.toLowerCase()))
                {
                    foodLists.add(food);
                }
            }
        }

        GridLayoutManager foodLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(foodLayoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(), foodLists);
        collectionView.setAdapter(foodAdapter);
    }

    private void showObjectCollection(boolean filtered, String query)
    {
        selectedItem = 2;
        ArrayList<ObjectModel> objectLists = new ArrayList<>();

        if(!filtered)
        {
            objectLists = inventoryModel.getObjectLists();
        }else
        {
            for(ObjectModel object : inventoryModel.getObjectLists())
            {
                if(object.getObjectName().toLowerCase().contains(query.toLowerCase()))
                {
                    objectLists.add(object);
                }
            }
        }

        GridLayoutManager objectLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(objectLayoutManager);
        ObjectAdapter objectAdapter = new ObjectAdapter(getActivity(), objectLists);
        collectionView.setAdapter(objectAdapter);
    }
}