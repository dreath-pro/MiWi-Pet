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

import com.example.miwipet.R;
import com.example.miwipet.adapters.OfferAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.utils.EggSource;

import java.util.ArrayList;
import java.util.Random;

public class FindOfferFragment extends Fragment {
    RecyclerView offerView;
    Button backPageButton, nextPageButton;
    Button searchOfferButton, refreshOfferButton, lookupButton;
    TextView pageIndicator;

    private Random random = new Random();
    private Context context;

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
        for(int i = 0; i <= 9; i++)
        {
            offerModels.add(new OfferModel(generateName(), generateProfile(), generateTradeHistory(),
                    generateTradeHistory(), generateWant(), generateWant(), 0));
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

    private InventoryModel generateWant() {
        InventoryModel inventoryModel = new InventoryModel();

        EggSource eggSource = new EggSource();
        ArrayList<EggModel> eggModel = eggSource.getAllEggs();

        int selectedEgg = random.nextInt(eggModel.size());

        PetModel petModel = new PetModel(eggModel.get(selectedEgg).getPetName(),
                eggModel.get(selectedEgg).getPetImage(), eggModel.get(selectedEgg).getAge(),
                eggModel.get(selectedEgg).getType(), eggModel.get(selectedEgg).getRarityText(),
                50, 0);

        int repeatExp = random.nextInt(75) + 1;
        petModel.setExp(10 * repeatExp);

        inventoryModel.addPetLists(petModel);

        return inventoryModel;
    }
}