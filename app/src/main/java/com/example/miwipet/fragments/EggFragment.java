package com.example.miwipet.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.miwipet.R;
import com.example.miwipet.adapters.EggSelectionAdapter;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.NormalEgg;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class EggFragment extends Fragment {
    Button backButton;
    RecyclerView eggSelection;
    Context context;
    ImageView eggImage;

    private ArrayList<EggModel> eggInventory;
    private ArrayList<EggModel> incubated;

    public EggFragment(ArrayList<EggModel> eggInventory, ArrayList<EggModel> incubated, ImageView eggImage) {
        this.eggInventory = eggInventory;
        this.eggImage = eggImage;
        this.incubated = incubated;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_egg, container, false);

        backButton = view.findViewById(R.id.backButton);
        eggSelection = view.findViewById(R.id.eggSelection);

        EggSelectionAdapter eggSelectionAdapter =
                new EggSelectionAdapter(context, eggInventory, incubated,
                        requireActivity().getSupportFragmentManager(), eggImage);
        eggSelection.setAdapter(eggSelectionAdapter);
        eggSelection.setLayoutManager(new LinearLayoutManager(context));

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        this.context = context;
    }
}