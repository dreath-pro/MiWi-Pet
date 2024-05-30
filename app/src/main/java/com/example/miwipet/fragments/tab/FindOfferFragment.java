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
import android.widget.TextView;
import android.widget.Toast;

import com.example.miwipet.R;
import com.example.miwipet.adapters.OfferAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.utils.EggSource;
import com.example.miwipet.utils.FoodSource;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class FindOfferFragment extends Fragment {
    RecyclerView offerView;
    Button backPageButton, nextPageButton;
    Button searchOfferButton, refreshOfferButton, lookupButton;
    TextView pageIndicator, errorText;

    private Random random = new Random();
    private Context context;
    private int wantItemSingle;
    private ArrayList<Integer> offererItemSeries = new ArrayList<>();

    private ArrayList<OfferModel> offerModels = new ArrayList<>();
    private ArrayList<OfferModel> dividedOfferModels = new ArrayList<>();
    private OfferAdapter offerAdapter;

    private void generateComponents(View view) {
        offerView = view.findViewById(R.id.offerView);
        backPageButton = view.findViewById(R.id.backPageButton);
        nextPageButton = view.findViewById(R.id.nextPageButton);
        searchOfferButton = view.findViewById(R.id.searchOfferButton);
        refreshOfferButton = view.findViewById(R.id.refreshOfferButton);
        lookupButton = view.findViewById(R.id.lookupButton);
        pageIndicator = view.findViewById(R.id.pageIndicator);
        errorText = view.findViewById(R.id.errorText);

        errorText.setVisibility(View.INVISIBLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_offer, container, false);
        generateComponents(view);

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

        refreshOffers();

        offerAdapter = new OfferAdapter(requireActivity(), offerModels);
        offerView.setAdapter(offerAdapter);
        offerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
    }

    private void refreshOffers() {
        for (int i = 1; i <= 10; i++) {
            offerModels.add(new OfferModel(generateName(), generateProfile(), generateTradeHistory(),
                    generateTradeHistory(), generateItem(true), generateItem(false)));

            offerModels.get(i - 1).setWantItemSingle(wantItemSingle);
            offerModels.get(i - 1).setOffererItemSeries(offererItemSeries);
        }
    }

    private String generateName() {
        String[] nameList = {"Lily X boy", "Amandiax160", "cutiepie_x069", "dreath_pro", "Jade the grinder",
                "Liam Naz", "Marzie262", "Nath"};
        int selectedName = random.nextInt(nameList.length);

        return nameList[selectedName];
    }

    private String generateProfile() {
        String[] userProfile = {"profile_lilboy", "profile_lilgal"};
        int selectedProfile = random.nextInt(userProfile.length);

        return userProfile[selectedProfile];
    }

    private int generateTradeHistory() {
        int tradeHistory = random.nextInt(1000) + 1;
        return tradeHistory;
    }

    private InventoryModel generateItem(boolean wantItem) {
        InventoryModel inventoryModel = new InventoryModel();
        EggSource eggSource = new EggSource();
        FoodSource foodSource = new FoodSource();

        int numberOfItems = random.nextInt(9);
        int selectedItem = random.nextInt(3);

        offererItemSeries.clear();

        if(!wantItem)
        {
            offererItemSeries.add(selectedItem);
        }else
        {
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
            }

            selectedItem = random.nextInt(3);
            if(!wantItem)
            {
                offererItemSeries.add(selectedItem);
            }
        }

        return inventoryModel;
    }
}