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
import android.widget.Toast;

import com.example.miwipet.R;
import com.example.miwipet.adapters.ForTradeAdapter;
import com.example.miwipet.adapters.LookingForAdapter;
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

public class MakeOfferFragment extends Fragment {
    private TextView errorText, pageIndicator;
    private RecyclerView forTradeView;
    private ImageView backPageButton, nextPageButton;
    private Button searchButton, refreshButton;

    private Random random = new Random();
    private Context context;
    private int wantItemSingle;
    private int page = 1;

    private InventoryModel yourInventory;
    private ArrayList<OfferModel> offerModels = new ArrayList<>();
    private ArrayList<OfferModel> dividedOfferModels = new ArrayList<>();
    private ForTradeAdapter forTradeAdapter;

    private TradeGeneration tradeGeneration = new TradeGeneration();

    private void generateComponents(View view) {
        errorText = view.findViewById(R.id.errorText);
        forTradeView = view.findViewById(R.id.forTradeView);
        backPageButton = view.findViewById(R.id.backPageButton);
        nextPageButton = view.findViewById(R.id.nextPageButton);
        pageIndicator = view.findViewById(R.id.pageIndicator);
        searchButton = view.findViewById(R.id.searchButton);
        refreshButton = view.findViewById(R.id.refreshButton);

        errorText.setVisibility(View.INVISIBLE);
    }

    public MakeOfferFragment(InventoryModel yourInventory) {
        this.yourInventory = yourInventory;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_make_offer, container, false);
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

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show();
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
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

        for (int i = 0; i <= 99; i++) {
            offerModels.add(new OfferModel(tradeGeneration.generateName(), tradeGeneration.generateProfile(),
                    tradeGeneration.generateTradeHistory(), tradeGeneration.generateTradeHistory(),
                    generateItem()));

            offerModels.get(i).setWantItemSingle(wantItemSingle);
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

        forTradeAdapter = new ForTradeAdapter(requireActivity(), dividedOfferModels, yourInventory, refreshButton);
        forTradeView.setAdapter(forTradeAdapter);
        forTradeView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private InventoryModel generateItem() {
        InventoryModel inventoryModel = new InventoryModel();
        EggSource eggSource = new EggSource();
        FoodSource foodSource = new FoodSource();
        ObjectSource objectSource = new ObjectSource();

        int selectedItem = random.nextInt(4);
        wantItemSingle = selectedItem;

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

        return inventoryModel;
    }
}