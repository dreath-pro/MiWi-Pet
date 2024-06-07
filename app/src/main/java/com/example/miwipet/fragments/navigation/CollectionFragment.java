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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class CollectionFragment extends Fragment {
    private RecyclerView collectionView;
    private Context context;
    private Button petsButton, objectsButton, foodsButton;
    private EditText inventorySearch;
    private ImageView backPageButton, nextPageButton;
    private TextView pageIndicator;

    private int page;
    private int maxPage;
    private int maxSize;
    private int selectedItem;
    private boolean lockSort;

    private InventoryModel inventoryModel;

    public CollectionFragment(InventoryModel inventoryModel, Context context) {
        this.inventoryModel = inventoryModel;
        this.context = context;

        lockSort = false;
        page = 1;
        maxPage = 1;
        maxSize = 50;
        selectedItem = 0;
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
                inventorySearch.setText("");
                page = 1;
                showPetCollection(false, "");
            }
        });

        objectsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorySearch.setText("");
                page = 1;
                showObjectCollection(false, "");
            }
        });

        foodsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorySearch.setText("");
                page = 1;
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
                    switch (selectedItem) {
                        case 0:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showPetCollection(false, "");
                            }else
                            {
                                showPetCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                        case 1:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showFoodCollection(false, "");
                            }else
                            {
                                showFoodCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                        case 2:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showObjectCollection(false, "");
                            }else
                            {
                                showObjectCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                    }
                }
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page++;
                if (page > maxPage) {
                    page = maxPage;
                } else {
                    switch (selectedItem) {
                        case 0:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showPetCollection(false, "");
                            }else
                            {
                                showPetCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                        case 1:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showFoodCollection(false, "");
                            }else
                            {
                                showFoodCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                        case 2:
                            if(inventorySearch.getText().toString().isEmpty())
                            {
                                showObjectCollection(false, "");
                            }else
                            {
                                showObjectCollection(true, inventorySearch.getText().toString());
                            }
                            break;
                    }
                }
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

        inventorySearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventorySearch.setText("");
            }
        });

        return view;
    }

    private void filterInventory(String query) {
        /**
         * items
         * 0 - pets
         * 1 - foods
         * 2 - objects
         */
        if (!query.isEmpty()) {
            switch (selectedItem) {
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
        } else {
            switch (selectedItem) {
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

    private InventoryModel sortInventory(InventoryModel inventoryModel) {
        InventoryModel sortedInventory = new InventoryModel();

        for (int i = inventoryModel.getPetLists().size() - 1; i >= 0; i--) {
            sortedInventory.getPetLists().add(inventoryModel.getPetLists().get(i));
        }

        for (int i = inventoryModel.getFoodLists().size() - 1; i >= 0; i--) {
            sortedInventory.getFoodLists().add(inventoryModel.getFoodLists().get(i));
        }

        for (int i = inventoryModel.getObjectLists().size() - 1; i >= 0; i--) {
            sortedInventory.getObjectLists().add(inventoryModel.getObjectLists().get(i));
        }

        lockSort = true;

        return sortedInventory;
    }

    private void showPetCollection(boolean filtered, String query) {
        selectedItem = 0;
        ArrayList<PetModel> petLists = new ArrayList<>();

        if (!lockSort) {
            inventoryModel = sortInventory(inventoryModel);
        }

        if (!filtered) {
            petLists = inventoryModel.getPetLists();
        } else {
            for (PetModel pet : inventoryModel.getPetLists()) {
                if (pet.getPetName().toLowerCase().contains(query.toLowerCase())) {
                    petLists.add(pet);
                }
            }
        }


        int minIndex = 0, maxIndex = maxSize - 1;
        if (petLists.size() < maxSize) {
            maxIndex = petLists.size() - 1;
        }

        maxPage = 1;
        int tempMaxSize = maxSize;
        for (int i = 1; i <= petLists.size(); i++) {
            if (i > tempMaxSize) {
                maxPage++;
                tempMaxSize += maxSize;
            }
        }

        for (int i = 1; i <= page - 1; i++) {
            minIndex = maxIndex + 1;
            maxIndex += maxSize;
        }

        ArrayList<PetModel> tempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > petLists.size() - 1)
            {
                break;
            }
            tempList.add(petLists.get(i));
        }

        petLists = tempList;


        GridLayoutManager petLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(petLayoutManager);
        PetAdapter petAdapter = new PetAdapter(getActivity(), petLists);
        collectionView.setAdapter(petAdapter);

        pageIndicator.setText(page + " out of " + maxPage);
    }

    private void showFoodCollection(boolean filtered, String query) {
        selectedItem = 1;
        ArrayList<FoodModel> foodLists = new ArrayList<>();

        if (!lockSort) {
            inventoryModel = sortInventory(inventoryModel);
        }

        if (!filtered) {
            foodLists = inventoryModel.getFoodLists();
        } else {
            for (FoodModel food : inventoryModel.getFoodLists()) {
                if (food.getFoodName().toLowerCase().contains(query.toLowerCase())) {
                    foodLists.add(food);
                }
            }
        }


        int minIndex = 0, maxIndex = maxSize - 1;
        if (foodLists.size() < maxSize) {
            maxIndex = foodLists.size() - 1;
        }

        maxPage = 1;
        int tempMaxSize = maxSize;
        for (int i = 1; i <= foodLists.size(); i++) {
            if (i > tempMaxSize) {
                maxPage++;
                tempMaxSize += maxSize;
            }
        }

        for (int i = 1; i <= page - 1; i++) {
            minIndex = maxIndex + 1;
            maxIndex += maxSize;
        }

        ArrayList<FoodModel> tempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > foodLists.size() - 1)
            {
                break;
            }
            tempList.add(foodLists.get(i));
        }

        foodLists = tempList;


        GridLayoutManager foodLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(foodLayoutManager);
        FoodAdapter foodAdapter = new FoodAdapter(getActivity(), foodLists);
        collectionView.setAdapter(foodAdapter);

        pageIndicator.setText(page + " out of " + maxPage);
    }

    private void showObjectCollection(boolean filtered, String query) {
        selectedItem = 2;
        ArrayList<ObjectModel> objectLists = new ArrayList<>();

        if (!lockSort) {
            inventoryModel = sortInventory(inventoryModel);
        }

        if (!filtered) {
            objectLists = inventoryModel.getObjectLists();
        } else {
            for (ObjectModel object : inventoryModel.getObjectLists()) {
                if (object.getObjectName().toLowerCase().contains(query.toLowerCase())) {
                    objectLists.add(object);
                }
            }
        }


        int minIndex = 0, maxIndex = maxSize - 1;
        if (objectLists.size() < maxSize) {
            maxIndex = objectLists.size() - 1;
        }

        maxPage = 1;
        int tempMaxSize = maxSize;
        for (int i = 1; i <= objectLists.size(); i++) {
            if (i > tempMaxSize) {
                maxPage++;
                tempMaxSize += maxSize;
            }
        }

        for (int i = 1; i <= page - 1; i++) {
            minIndex = maxIndex + 1;
            maxIndex += maxSize;
        }

        ArrayList<ObjectModel> tempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if(i > objectLists.size() - 1)
            {
                break;
            }
            tempList.add(objectLists.get(i));
        }

        objectLists = tempList;


        GridLayoutManager objectLayoutManager = new GridLayoutManager(context, 2);
        collectionView.setLayoutManager(objectLayoutManager);
        ObjectAdapter objectAdapter = new ObjectAdapter(getActivity(), objectLists);
        collectionView.setAdapter(objectAdapter);

        pageIndicator.setText(page + " out of " + maxPage);
    }
}