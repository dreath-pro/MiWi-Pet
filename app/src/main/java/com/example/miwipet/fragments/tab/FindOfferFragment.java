package com.example.miwipet.fragments.tab;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.miwipet.adapters.LookingForAdapter;
import com.example.miwipet.adapters.ObjectAdapter;
import com.example.miwipet.adapters.PetAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.utils.EggSource;
import com.example.miwipet.utils.FoodSource;
import com.example.miwipet.utils.ObjectSource;
import com.example.miwipet.utils.TradeGeneration;

import java.util.ArrayList;
import java.util.Random;

public class FindOfferFragment extends Fragment {
    private RecyclerView offerView;
    private ImageView backPageButton, nextPageButton;
    private Button refreshOfferButton, lookupButton;
    private TextView pageIndicator, errorText;

    private Random random = new Random();
    private Context context;
    private int wantItemSingle;
    private int page = 1;

    private ArrayList<ArrayList<Integer>> listOfOffererItemSeries = new ArrayList<>();

    private InventoryModel yourInventory;
    private ArrayList<OfferModel> offerModels = new ArrayList<>();
    private ArrayList<OfferModel> dividedOfferModels = new ArrayList<>();
    private LookingForAdapter lookingForAdapter;

    private TradeGeneration tradeGeneration = new TradeGeneration();

    private void generateComponents(View view) {
        offerView = view.findViewById(R.id.offerView);
        backPageButton = view.findViewById(R.id.backPageButton);
        nextPageButton = view.findViewById(R.id.nextPageButton);
        refreshOfferButton = view.findViewById(R.id.refreshOfferButton);
        lookupButton = view.findViewById(R.id.lookupButton);
        pageIndicator = view.findViewById(R.id.pageIndicator);
        errorText = view.findViewById(R.id.errorText);

        errorText.setVisibility(View.INVISIBLE);
    }

    public FindOfferFragment(InventoryModel yourInventory) {
        this.yourInventory = yourInventory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_offer, container, false);
        generateComponents(view);

        backPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page--;
                if (page < 1) {
                    page = 1;
                } else {
                    resetAdapter();
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
                    resetAdapter();
                }

                pageIndicator.setText(page + " out of 10");
            }
        });

        lookupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInventory();
            }
        });

        refreshOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page = 1;
                pageIndicator.setText(page + " out of 10");

                generateOffers();
                resetAdapter();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        generateOffers();
        resetAdapter();
    }

    private void generateOffers() {
        offerModels.clear();
        listOfOffererItemSeries.clear();

        for (int i = 0; i <= 99; i++) {
            offerModels.add(new OfferModel(tradeGeneration.generateName(), tradeGeneration.generateProfile(),
                    tradeGeneration.generateTradeHistory(), tradeGeneration.generateTradeHistory(),
                    generateItem(true), generateItem(false)));

            offerModels.get(i).setWantItemSingle(wantItemSingle);
            offerModels.get(i).setOffererItemSeries(listOfOffererItemSeries.get(i));
        }
    }

    private void resetAdapter() {
        int pageMinIndex = 0, pageMaxIndex = 0;
        dividedOfferModels.clear();

        switch (page) {
            case 1:
                pageMinIndex = 1;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 2:
                pageMinIndex = 11;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 3:
                pageMinIndex = 21;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 4:
                pageMinIndex = 31;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 5:
                pageMinIndex = 41;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 6:
                pageMinIndex = 51;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 7:
                pageMinIndex = 61;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 8:
                pageMinIndex = 71;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 9:
                pageMinIndex = 81;
                pageMaxIndex = (pageMinIndex + 9);
                break;
            case 10:
                pageMinIndex = 91;
                pageMaxIndex = (pageMinIndex + 9);
                break;
        }

        pageMinIndex -= 1;
        pageMaxIndex -= 1;

        for (int i = pageMinIndex; i <= pageMaxIndex; i++) {
            dividedOfferModels.add(offerModels.get(i));
        }

        lookingForAdapter = new LookingForAdapter(requireActivity(), dividedOfferModels, yourInventory, refreshOfferButton);
        offerView.setAdapter(lookingForAdapter);
        offerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private InventoryModel generateItem(boolean wantItem) {
        InventoryModel inventoryModel = new InventoryModel();
        EggSource eggSource = new EggSource();
        FoodSource foodSource = new FoodSource();
        ObjectSource objectSource = new ObjectSource();

        int numberOfItems;
        int selectedItem = random.nextInt(4);

        ArrayList<Integer> offererItemSeries = new ArrayList<>();

        if (!wantItem) {
            numberOfItems = random.nextInt(9);
            offererItemSeries.add(selectedItem);
        } else {
            numberOfItems = random.nextInt(1);
            wantItemSingle = selectedItem;
        }

        for (int i = 0; i <= numberOfItems; i++) {
            switch (selectedItem) {
                case 0:
                    ArrayList<EggModel> eggModel = eggSource.getAllEggs();
                    int selectedEgg = random.nextInt(eggModel.size());

                    PetModel petModel = new PetModel(eggModel.get(selectedEgg).getPetName(),
                            eggModel.get(selectedEgg).getPetImage(), eggModel.get(selectedEgg).getAge(),
                            eggModel.get(selectedEgg).getType(), eggModel.get(selectedEgg).getRarityText(),
                            50, 0);

                    int repeatExp = random.nextInt(751);
                    petModel.setExp(repeatExp);

                    inventoryModel.addPetLists(petModel);
                    break;
                case 1:
                    ArrayList<EggModel> eggModel2 = eggSource.getAllEggs();

                    int selectedEgg2 = random.nextInt(eggModel2.size());
                    inventoryModel.addEggLists(eggModel2.get(selectedEgg2));
                    break;
                case 2:
                    ArrayList<FoodModel> foodModels = foodSource.getAllFoods();

                    int selectedFood = random.nextInt(foodModels.size());
                    inventoryModel.addFoodLists(foodModels.get(selectedFood));
                    break;
                case 3:
                    ArrayList<ObjectModel> objectModels = objectSource.getAllObjects();

                    int selectedObject = random.nextInt(objectModels.size());
                    inventoryModel.addObjectLists(objectModels.get(selectedObject));
                    break;
            }

            if (i == numberOfItems) {
                break;
            }

            selectedItem = random.nextInt(4);
            if (!wantItem) {
                offererItemSeries.add(selectedItem);
            }
        }

        if (!wantItem) {
            listOfOffererItemSeries.add(offererItemSeries);
        }

        return inventoryModel;
    }

    private int inventoryPage = 1;
    private int inventoryMaxPage = 1;
    private int inventoryMaxSize = 50;
    private int selectedItem = 0;
    private boolean lockSort = false;

    private EditText inventorySearch;
    private Button petsButton, objectsButton, foodsButton;
    private RecyclerView collectionView;
    private ImageView dialogBackPageButton;
    private ImageView dialogNextPageButton;
    private TextView dialogPageIndicator;

    private void showInventory() {
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.fragment_collection);

        inventorySearch = dialog.findViewById(R.id.inventorySearch);
        petsButton = dialog.findViewById(R.id.petsButton);
        objectsButton = dialog.findViewById(R.id.objectsButton);
        foodsButton = dialog.findViewById(R.id.foodsButton);
        collectionView = dialog.findViewById(R.id.collectionView);
        dialogBackPageButton = dialog.findViewById(R.id.backPageButton);
        dialogNextPageButton = dialog.findViewById(R.id.nextPageButton);
        dialogPageIndicator = dialog.findViewById(R.id.pageIndicator);

        showCollection(selectedItem, false, "");

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
                    if (inventorySearch.getText().toString().isEmpty()) {
                        showCollection(selectedItem, false, "");
                    } else {
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
                    if (inventorySearch.getText().toString().isEmpty()) {
                        showCollection(selectedItem, false, "");
                    } else {
                        showCollection(selectedItem, true, inventorySearch.getText().toString());
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

        dialog.show();
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

        switch (selectedItem) {
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

        switch (selectedItem) {
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
            if (i > petLists.size() - 1) {
                break;
            }
            petTempList.add(petLists.get(i));
        }
        petLists = petTempList;

        ArrayList<FoodModel> foodTempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if (i > foodLists.size() - 1) {
                break;
            }
            foodTempList.add(foodLists.get(i));
        }
        foodLists = foodTempList;

        ArrayList<ObjectModel> objectTempList = new ArrayList<>();
        for (int i = minIndex; i <= maxIndex; i++) {
            if (i > objectLists.size() - 1) {
                break;
            }
            objectTempList.add(objectLists.get(i));
        }
        objectLists = objectTempList;

        switch (selectedItem) {
            case 0:
                GridLayoutManager petLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(petLayoutManager);
                PetAdapter petAdapter = new PetAdapter(getActivity(), petLists);
                collectionView.setAdapter(petAdapter);
                break;
            case 1:
                GridLayoutManager foodLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(foodLayoutManager);
                FoodAdapter foodAdapter = new FoodAdapter(getActivity(), foodLists);
                collectionView.setAdapter(foodAdapter);
                break;
            case 2:
                GridLayoutManager objectLayoutManager = new GridLayoutManager(context, 2);
                collectionView.setLayoutManager(objectLayoutManager);
                ObjectAdapter objectAdapter = new ObjectAdapter(getActivity(), objectLists);
                collectionView.setAdapter(objectAdapter);
                break;
        }

        dialogPageIndicator.setText(inventoryPage + " out of " + inventoryMaxPage);
    }

    private void filterInventory(String query) {
        inventoryPage = 1;
        if (!query.isEmpty()) {
            showCollection(selectedItem, true, query);
        } else {
            showCollection(selectedItem, false, "");
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
}