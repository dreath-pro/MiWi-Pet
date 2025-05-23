package com.example.miwipet.fragments.tab;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miwipet.R;
import com.example.miwipet.adapters.LookingForAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.logics.EggSource;
import com.example.miwipet.logics.FoodSource;
import com.example.miwipet.logics.MiniInventory;
import com.example.miwipet.logics.ObjectSource;
import com.example.miwipet.logics.TradeGeneration;

import java.util.ArrayList;
import java.util.Random;

public class FindOfferFragment extends Fragment implements LookingForAdapter.OnItemAcceptListener{
    private MiniInventory miniInventory;

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
                miniInventory.showInventory();
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

        lookingForAdapter = new LookingForAdapter(requireActivity(), dividedOfferModels, yourInventory, refreshOfferButton);
        lookingForAdapter.setOnItemAcceptListener(this);

        return view;
    }

    @Override
    public void onItemAccepted(OfferModel offerModel) {
        refreshOfferButton.performClick();
    }

    @Override
    public void onItemAccepted() {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        miniInventory = new MiniInventory(requireActivity(), yourInventory);
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
}