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
import com.example.miwipet.adapters.FoodShopSelectionAdapter;
import com.example.miwipet.adapters.ObjectShopSelectionAdapter;
import com.example.miwipet.database.EggDisplayDatabase;
import com.example.miwipet.database.FoodDisplayDatabase;
import com.example.miwipet.database.ObjectDisplayDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.TimeModel;
import com.example.miwipet.logics.EggSource;
import com.example.miwipet.logics.FoodSource;
import com.example.miwipet.logics.ObjectSource;

import java.util.ArrayList;

public class StoreFragment extends Fragment {
    private ArrayList<EggModel> eggDisplay = new ArrayList<>();
    private ArrayList<FoodModel> foodDisplay = new ArrayList<>();
    private ArrayList<ObjectModel> objectDisplay = new ArrayList<>();

    private EggSource eggSource = new EggSource();
    private FoodSource foodSource = new FoodSource();
    private ObjectSource objectSource = new ObjectSource();

    private EggShopSelectionAdapter eggShopSelectionAdapter;
    private FoodShopSelectionAdapter foodShopSelectionAdapter;
    private ObjectShopSelectionAdapter objectShopSelectionAdapter;

    private EggDisplayDatabase eggDisplayDatabase;
    private FoodDisplayDatabase foodDisplayDatabase;
    private ObjectDisplayDatabase objectDisplayDatabase;


    private InventoryModel inventoryModels;
    private TimeModel timeModel;
    private RecyclerView eggShopSelection, foodShopSelection, objectShopSelection;
    private Context context;

    private TextView chipToken, glazeToken;

    public StoreFragment(InventoryModel inventoryModels, TextView chipToken, TextView glazeToken, TimeModel timeModel) {
        this.inventoryModels = inventoryModels;
        this.chipToken = chipToken;
        this.glazeToken = glazeToken;
        this.timeModel = timeModel;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_store, container, false);

        eggShopSelection = view.findViewById(R.id.eggShopSelection);
        objectShopSelection = view.findViewById(R.id.objectShopSelection);
        foodShopSelection = view.findViewById(R.id.foodShopSelection);

        generateEggDisplay();
        generateFoodDisplay();
        generateObjectDisplay();

        eggShopSelectionAdapter = new EggShopSelectionAdapter(context, eggSource, eggDisplay, chipToken, glazeToken, inventoryModels);
        eggShopSelection.setAdapter(eggShopSelectionAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        eggShopSelection.setLayoutManager(layoutManager);

        foodShopSelectionAdapter = new FoodShopSelectionAdapter(context, foodSource, foodDisplay, chipToken, glazeToken, inventoryModels);
        foodShopSelection.setAdapter(foodShopSelectionAdapter);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        foodShopSelection.setLayoutManager(layoutManager2);

        objectShopSelectionAdapter = new ObjectShopSelectionAdapter(context, objectSource, objectDisplay, chipToken, glazeToken, inventoryModels);
        objectShopSelection.setAdapter(objectShopSelectionAdapter);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        objectShopSelection.setLayoutManager(layoutManager3);

        return view;
    }

    private void generateObjectDisplay() {
        if (!objectDisplayDatabase.doesDataExist()) {
            objectDisplay.clear();
            objectDisplayDatabase.clearDisplay();
            objectDisplay.addAll(objectSource.getStoreObjects());

            for (ObjectModel object : objectDisplay) {
                objectDisplayDatabase.generateTable(object);
            }
        }

        if (timeModel.isNewDay()) {
            objectDisplay.clear();
            objectDisplayDatabase.clearDisplay();
            objectDisplay.addAll(objectSource.getStoreObjects());

            for (ObjectModel object : objectDisplay) {
                objectDisplayDatabase.generateTable(object);
            }

            timeModel.setLastTimeLogin(timeModel.getCurrentTimeLogin());
            timeModel.setLastDayLogin(timeModel.getCurrentDayLogin());
            timeModel.setLastMonthLogin(timeModel.getCurrentMonthLogin());
            timeModel.setLastYearLogin(timeModel.getCurrentYearLogin());
        } else {
            objectDisplay.clear();
            ArrayList<String> objectNames;
            objectNames = objectDisplayDatabase.getDisplayList();
            objectDisplay = objectSource.getObjectByString(objectNames);
        }
    }

    private void generateFoodDisplay() {
        if (!foodDisplayDatabase.doesDataExist()) {
            foodDisplay.clear();
            foodDisplayDatabase.clearDisplay();
            foodDisplay.addAll(foodSource.getStoreFoods());

            for (FoodModel food : foodDisplay) {
                foodDisplayDatabase.generateTable(food);
            }
        }

        if (timeModel.isNewDay()) {
            foodDisplay.clear();
            foodDisplayDatabase.clearDisplay();
            foodDisplay.addAll(foodSource.getStoreFoods());

            for (FoodModel food : foodDisplay) {
                foodDisplayDatabase.generateTable(food);
            }

            timeModel.setLastTimeLogin(timeModel.getCurrentTimeLogin());
            timeModel.setLastDayLogin(timeModel.getCurrentDayLogin());
            timeModel.setLastMonthLogin(timeModel.getCurrentMonthLogin());
            timeModel.setLastYearLogin(timeModel.getCurrentYearLogin());
        } else {
            foodDisplay.clear();
            ArrayList<String> foodNames;
            foodNames = foodDisplayDatabase.getDisplayList();
            foodDisplay = foodSource.getFoodsByString(foodNames);
        }
    }

    private void generateEggDisplay() {
        if (!eggDisplayDatabase.doesDataExist()) {
            eggDisplay.clear();
            eggDisplayDatabase.clearDisplay();
            eggDisplay.addAll(eggSource.getStoreEggs());

            for (EggModel egg : eggDisplay) {
                eggDisplayDatabase.generateTable(egg);
            }
        }

        if (timeModel.isNewDay()) {
            eggDisplay.clear();
            eggDisplayDatabase.clearDisplay();
            eggDisplay.addAll(eggSource.getStoreEggs());

            for (EggModel egg : eggDisplay) {
                eggDisplayDatabase.generateTable(egg);
            }

            timeModel.setLastTimeLogin(timeModel.getCurrentTimeLogin());
            timeModel.setLastDayLogin(timeModel.getCurrentDayLogin());
            timeModel.setLastMonthLogin(timeModel.getCurrentMonthLogin());
            timeModel.setLastYearLogin(timeModel.getCurrentYearLogin());
        } else {
            eggDisplay.clear();
            ArrayList<String> eggNames;
            eggNames = eggDisplayDatabase.getDisplayList();
            eggDisplay = eggSource.getEggsByString(eggNames);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;

        eggDisplayDatabase = new EggDisplayDatabase(context);
        foodDisplayDatabase = new FoodDisplayDatabase(context);
        objectDisplayDatabase = new ObjectDisplayDatabase(context);
    }
}