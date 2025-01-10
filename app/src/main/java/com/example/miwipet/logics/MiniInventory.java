package com.example.miwipet.logics;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.adapters.FoodAdapter;
import com.example.miwipet.adapters.ObjectAdapter;
import com.example.miwipet.adapters.OfferedPetAdapter;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MiniInventory {
    private Context context;
    private Activity activity;

    private Dialog dialog;
    private EditText inventorySearch;
    private Button petsButton, objectsButton, foodsButton;
    private RecyclerView collectionView;
    private ImageView dialogBackPageButton, dialogNextPageButton;
    private TextView dialogPageIndicator;

    private InventoryModel yourInventory;
    private int inventoryPage = 1;
    private int inventoryMaxPage = 1;
    private int inventoryMaxSize = 50;
    private int selectedItem = 0;
    private boolean lockSort = false;

    public MiniInventory(Activity activity, InventoryModel inventoryModel) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.yourInventory = inventoryModel;
    }

    public void showInventory() {
        dialog = new Dialog(activity);
        dialog.setContentView(R.layout.fragment_collection);

        initializeViews();
        showCollection(selectedItem, false, "");
        setupListeners();
        dialog.show();
    }

    private void initializeViews() {
        inventorySearch = dialog.findViewById(R.id.inventorySearch);
        petsButton = dialog.findViewById(R.id.petsButton);
        objectsButton = dialog.findViewById(R.id.objectsButton);
        foodsButton = dialog.findViewById(R.id.foodsButton);
        collectionView = dialog.findViewById(R.id.collectionView);
        dialogBackPageButton = dialog.findViewById(R.id.backPageButton);
        dialogNextPageButton = dialog.findViewById(R.id.nextPageButton);
        dialogPageIndicator = dialog.findViewById(R.id.pageIndicator);
    }

    private void setupListeners() {
        petsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 0;
                inventorySearch.setText("");
                inventoryPage = 1;
                showCollection(selectedItem, false, "");
            }
        });

        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 1;
                inventorySearch.setText("");
                inventoryPage = 1;
                showCollection(selectedItem, false, "");
            }
        });

        objectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = 2;
                inventorySearch.setText("");
                inventoryPage = 1;
                showCollection(selectedItem, false, "");
            }
        });

        dialogBackPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventoryPage--;
                if (inventoryPage < 1) {
                    inventoryPage = 1;
                } else {
                    if(inventorySearch.getText().toString().isEmpty())
                    {
                        showCollection(selectedItem, false, "");
                    }else
                    {
                        showCollection(selectedItem, true, inventorySearch.getText().toString());
                    }
                }
            }
        });

        dialogNextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventoryPage++;
                if (inventoryPage > inventoryMaxPage) {
                    inventoryPage = inventoryMaxPage;
                } else {
                    if(inventorySearch.getText().toString().isEmpty())
                    {
                        showCollection(selectedItem, false, "");
                    }else
                    {
                        showCollection(selectedItem, true, inventorySearch.getText().toString());
                    }
                }
            }
        });

        inventorySearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterInventory(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        inventorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorySearch.setText("");
            }
        });
    }

    private void showCollection(int selectedItem, boolean filtered, String query) {
        ArrayList<FoodModel> foodLists = new ArrayList<>();
        ArrayList<PetModel> petLists = new ArrayList<>();
        ArrayList<ObjectModel> objectLists = new ArrayList<>();

        if (!lockSort) {
            yourInventory = sortInventory(yourInventory);
        }

        if (!filtered) {
            petLists = yourInventory.getPetLists();
            foodLists = yourInventory.getFoodLists();
            objectLists = yourInventory.getObjectLists();
        } else {
            for (PetModel pet : yourInventory.getPetLists()) {
                if (pet.getPetName().toLowerCase().contains(query.toLowerCase())) {
                    petLists.add(pet);
                }
            }

            for (FoodModel food : yourInventory.getFoodLists()) {
                if (food.getFoodName().toLowerCase().contains(query.toLowerCase())) {
                    foodLists.add(food);
                }
            }

            for (ObjectModel object : yourInventory.getObjectLists()) {
                if (object.getObjectName().toLowerCase().contains(query.toLowerCase())) {
                    objectLists.add(object);
                }
            }
        }

        int minIndex = 0, maxIndex = inventoryMaxSize - 1;

        switch (selectedItem)
        {
            case 0:
                if (petLists.size() < inventoryMaxSize) {
                    maxIndex = petLists.size() - 1;
                }
                break;
            case 1:
                if (foodLists.size() < inventoryMaxSize) {
                    maxIndex = foodLists.size() - 1;
                }
                break;
            case 2:
                if (objectLists.size() < inventoryMaxSize) {
                    maxIndex = objectLists.size() - 1;
                }
                break;
        }

        inventoryMaxPage = 1;
        int tempMaxSize = inventoryMaxSize;

        switch (selectedItem)
        {
            case 0:
                for (int i = 1; i <= petLists.size(); i++) {
                    if (i > tempMaxSize) {
                        inventoryMaxPage++;
                        tempMaxSize += inventoryMaxSize;
                    }
                }
                break;
            case 1:
                for (int i = 1; i <= foodLists.size(); i++) {
                    if (i > tempMaxSize) {
                        inventoryMaxPage++;
                        tempMaxSize += inventoryMaxSize;
                    }
                }
                break;
            case 2:
                for (int i = 1; i <= objectLists.size(); i++) {
                    if (i > tempMaxSize) {
                        inventoryMaxPage++;
                        tempMaxSize += inventoryMaxSize;
                    }
                }
                break;
        }

        for (int i = 1; i <= inventoryPage - 1; i++) {
            minIndex = maxIndex + 1;
            maxIndex += inventoryMaxSize;
        }

        ArrayList<PetModel> petTempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > petLists.size() - 1)
            {
                break;
            }
            petTempList.add(petLists.get(i));
        }
        petLists = petTempList;

        ArrayList<FoodModel> foodTempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > foodLists.size() - 1)
            {
                break;
            }
            foodTempList.add(foodLists.get(i));
        }
        foodLists = foodTempList;

        ArrayList<ObjectModel> objectTempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > objectLists.size() - 1)
            {
                break;
            }
            objectTempList.add(objectLists.get(i));
        }
        objectLists = objectTempList;

        switch (selectedItem)
        {
            case 0:
                GridLayoutManager petLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(petLayoutManager);
                OfferedPetAdapter offeredPetAdapter = new OfferedPetAdapter(activity, petLists, (OfferedPetAdapter.OnPetSelectedListener) activity);
                collectionView.setAdapter(offeredPetAdapter);
                break;
            case 1:
                GridLayoutManager foodLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(foodLayoutManager);
                FoodAdapter foodAdapter = new FoodAdapter(activity, foodLists);
                collectionView.setAdapter(foodAdapter);
                break;
            case 2:
                GridLayoutManager objectLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(objectLayoutManager);
                ObjectAdapter objectAdapter = new ObjectAdapter(activity, objectLists);
                collectionView.setAdapter(objectAdapter);
                break;
        }

        dialogPageIndicator.setText(inventoryPage + " out of " + inventoryMaxPage);
    }

    private void setRecyclerView(List<?> itemList, RecyclerView.Adapter<?> adapter) {
        collectionView.setLayoutManager(new GridLayoutManager(context, 2));
        collectionView.setAdapter(adapter);
    }

    private void filterInventory(String query) {
        inventoryPage = 1;
        if (query.isEmpty()) {
            showCollection(selectedItem, false, "");
        } else {
            showCollection(selectedItem, true, query);
        }
    }

    private InventoryModel sortInventory(InventoryModel inventoryModel) {
        InventoryModel sortedInventory = new InventoryModel();
        sortedInventory.getPetLists().addAll(inventoryModel.getPetLists());
        sortedInventory.getFoodLists().addAll(inventoryModel.getFoodLists());
        sortedInventory.getObjectLists().addAll(inventoryModel.getObjectLists());

        Collections.reverse(sortedInventory.getPetLists());
        Collections.reverse(sortedInventory.getFoodLists());
        Collections.reverse(sortedInventory.getObjectLists());

        lockSort = true;
        return sortedInventory;
    }
}
