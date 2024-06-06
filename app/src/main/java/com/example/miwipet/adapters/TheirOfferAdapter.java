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
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class TheirOfferAdapter extends RecyclerView.Adapter<TheirOfferAdapter.MyViewHolder> {
    private final Context context;
    private final Activity activity;
    private final ArrayList<Integer> itemSeries;
    private final InventoryModel theirInventory;
    private int petCounter, eggCounter, foodCounter, objectCounter;

    public TheirOfferAdapter(Activity activity, ArrayList<Integer> itemSeries, InventoryModel theirInventory) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.itemSeries = itemSeries;
        this.theirInventory = theirInventory;

        resetCounters();
    }

    @NonNull
    @Override
    public TheirOfferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.their_offer, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheirOfferAdapter.MyViewHolder holder, int position) {
        switch (itemSeries.get(position)) {
            case 0:
                bindPetItem(holder);
                break;
            case 1:
                bindEggItem(holder);
                break;
            case 2:
                bindFoodItem(holder);
                break;
            case 3:
                bindObjectItem(holder);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemSeries.size();
    }

    private void bindPetItem(MyViewHolder holder) {
        if (petCounter < theirInventory.getPetLists().size()) {
            PetModel petModel = theirInventory.getPetLists().get(petCounter);
            int resourceId = getResourceId(petModel.getPetImage());

            int rarityColor = getColor(petModel.getRarityColor());
            int typeColor = getColor(petModel.getTypeColor());

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            petCounter++;
        } else {
            // Handle case where there are no more pets
            setPlaceholderProperties(holder);
        }
    }

    private void bindEggItem(MyViewHolder holder) {
        if (eggCounter < theirInventory.getEggLists().size()) {
            EggModel eggModel = theirInventory.getEggLists().get(eggCounter);
            int resourceId = getResourceId(eggModel.getEggImage());

            int rarityColor = getColor(R.color.common);
            int typeColor = getColor(R.color.white);

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            eggCounter++;
        } else {
            // Handle case where there are no more eggs
            setPlaceholderProperties(holder);
        }
    }

    private void bindFoodItem(MyViewHolder holder) {
        if (foodCounter < theirInventory.getFoodLists().size()) {
            FoodModel foodModel = theirInventory.getFoodLists().get(foodCounter);
            int resourceId = getResourceId(foodModel.getFoodImage());

            int rarityColor = getColor(foodModel.getRarityColor());
            int typeColor = getColor(R.color.white);

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            foodCounter++;
        } else {
            // Handle case where there are no more foods
            setPlaceholderProperties(holder);
        }
    }

    private void bindObjectItem(MyViewHolder holder) {
        if (objectCounter < theirInventory.getObjectLists().size()) {
            ObjectModel objectModel = theirInventory.getObjectLists().get(objectCounter);
            int resourceId = getResourceId(objectModel.getObjectImage());

            int rarityColor = getColor(objectModel.getRarityColor());
            int typeColor = getColor(R.color.object);

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            objectCounter++;
        } else {
            // Handle case where there are no more foods
            setPlaceholderProperties(holder);
        }
    }

    private int getResourceId(String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }

    private int getColor(int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    private void setItemProperties(MyViewHolder holder, int resourceId, int rarityColor, int typeColor) {
        holder.offererItemImage.setImageResource(resourceId);
        holder.offererItemImageContainer.setBackgroundColor(rarityColor);
        holder.offererItemImage.setBackgroundColor(typeColor);
    }

    private void setPlaceholderProperties(MyViewHolder holder) {
        int placeholderResourceId = getResourceId("icon_blank"); // Assuming there's a placeholder image
        int placeholderColor = getColor(R.color.common);

        holder.offererItemImage.setImageResource(placeholderResourceId);
        holder.offererItemImageContainer.setBackgroundColor(placeholderColor);
        holder.offererItemImage.setBackgroundColor(placeholderColor);
    }

    public void resetCounters() {
        petCounter = 0;
        eggCounter = 0;
        foodCounter = 0;
        objectCounter = 0;
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
