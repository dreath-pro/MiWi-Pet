package com.example.miwipet.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class TheirOfferAdapter extends RecyclerView.Adapter<TheirOfferAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<Integer> itemSeries;
    private InventoryModel inventoryModels;
    private int counter = 0;
    private int petCounter = 0, eggCounter = 0, foodCounter = 0;

    public TheirOfferAdapter(Activity activity, ArrayList<Integer> itemSeries, InventoryModel inventoryModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.itemSeries = itemSeries;
        this.inventoryModels = inventoryModels;

        counter = 0;
    }

    @NonNull
    @Override
    public TheirOfferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.their_offer, parent, false);
        return new TheirOfferAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheirOfferAdapter.MyViewHolder holder, int position) {
        int rarityColor = 0;
        int typeColor = 0;
        int resourceId = 0;

        //itemSeries.get(counter))
        switch (itemSeries.get(counter)) {
            case 0:
                PetModel petModel = inventoryModels.getPetLists().get(petCounter);
                resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

                rarityColor = ContextCompat.getColor(context, inventoryModels.getPetLists().get(petCounter).getRarityColor());
                typeColor = ContextCompat.getColor(context, inventoryModels.getPetLists().get(petCounter).getTypeColor());

                petCounter++;
                break;
            case 1:
                EggModel eggModel = inventoryModels.getEggLists().get(eggCounter);
                resourceId = context.getResources().getIdentifier(eggModel.getEggImage(), "drawable", context.getPackageName());

                rarityColor = ContextCompat.getColor(context, R.color.common);
                typeColor = ContextCompat.getColor(context, R.color.white);

                eggCounter++;
                break;
            case 2:
                FoodModel foodModel = inventoryModels.getFoodLists().get(foodCounter);
                resourceId = context.getResources().getIdentifier(foodModel.getFoodImage(), "drawable", context.getPackageName());

                rarityColor = ContextCompat.getColor(context, inventoryModels.getFoodLists().get(foodCounter).getRarityColor());
                typeColor = ContextCompat.getColor(context, R.color.white);

                foodCounter++;
                break;
        }

        holder.offererItemImage.setImageResource(resourceId);
        holder.offererItemImageContainer.setBackgroundColor(rarityColor);
        holder.offererItemImage.setBackgroundColor(typeColor);

        counter++;
    }

    @Override
    public int getItemCount() {
        int totalSize = 0;
        totalSize += inventoryModels.getPetLists().size();
        totalSize += inventoryModels.getEggLists().size();
        totalSize += inventoryModels.getFoodLists().size();
        return totalSize;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView offererItemImage;
        LinearLayout offererItemImageContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            offererItemImage = itemView.findViewById(R.id.offererItemImage);
            offererItemImageContainer = itemView.findViewById(R.id.offererItemImageContainer);
        }
    }
}
