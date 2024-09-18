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
import com.example.miwipet.utils.MiniInventory;

import java.util.ArrayList;

public class YourOfferAdapter extends RecyclerView.Adapter<YourOfferAdapter.MyViewHolder> {
    private MiniInventory miniInventory;

    private final Context context;
    private final Activity activity;
    private final ArrayList<Integer> itemSeries;
    private final InventoryModel yourSelectedItems;
    private final InventoryModel yourInventory;
    private int petCounter, eggCounter, foodCounter, objectCounter;

    public YourOfferAdapter(Context context, Activity activity, ArrayList<Integer> itemSeries, InventoryModel yourSelectedItems, InventoryModel yourInventory) {
        this.context = context;
        this.activity = activity;
        this.itemSeries = itemSeries != null ? itemSeries : new ArrayList<>();
        this.yourSelectedItems = yourSelectedItems != null ? yourSelectedItems : new InventoryModel();
        this.yourInventory = yourInventory;

        this.miniInventory = new MiniInventory(activity, yourInventory);

        resetOffer();
        resetCounters();
    }

    public void onPetSelected(PetModel petModel)
    {
        yourSelectedItems.addPetLists(petModel);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public YourOfferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.offer, parent, false);
        return new YourOfferAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull YourOfferAdapter.MyViewHolder holder, int position) {
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemSeries.get(holder.getBindingAdapterPosition()) == 0)
                {
                    if(yourSelectedItems.getPetLists().get(holder.getBindingAdapterPosition()).getPetName().equals("add"))
                    {
                        miniInventory.showInventory();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemSeries.size();
    }

    private void bindPetItem(YourOfferAdapter.MyViewHolder holder) {
        if (petCounter < yourSelectedItems.getPetLists().size()) {
            PetModel petModel = yourSelectedItems.getPetLists().get(petCounter);
            int resourceId;
            int rarityColor;
            int typeColor;

            if (!petModel.getPetName().equals("add")) {
                resourceId = getResourceId(petModel.getPetImage());
                rarityColor = getColor(petModel.getRarityColor());
                typeColor = getColor(petModel.getTypeColor());
            } else {
                resourceId = getResourceId("icon_add");
                rarityColor = getColor(R.color.green);
                typeColor = getColor(R.color.green);
            }

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            petCounter++;
        } else {
            // Handle case where there are no more pets
            setPlaceholderProperties(holder);
        }
    }

    private void bindEggItem(YourOfferAdapter.MyViewHolder holder) {
        if (eggCounter < yourSelectedItems.getEggLists().size()) {
            EggModel eggModel = yourSelectedItems.getEggLists().get(eggCounter);
            int resourceId = getResourceId(eggModel.getEggImage());

            int rarityColor = getColor(R.color.common);
            int typeColor = getColor(R.color.egg);

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            eggCounter++;
        } else {
            // Handle case where there are no more eggs
            setPlaceholderProperties(holder);
        }
    }

    private void bindFoodItem(YourOfferAdapter.MyViewHolder holder) {
        if (foodCounter < yourSelectedItems.getFoodLists().size()) {
            FoodModel foodModel = yourSelectedItems.getFoodLists().get(foodCounter);
            int resourceId = getResourceId(foodModel.getFoodImage());

            int rarityColor = getColor(foodModel.getRarityColor());
            int typeColor = getColor(R.color.food);

            setItemProperties(holder, resourceId, rarityColor, typeColor);

            foodCounter++;
        } else {
            // Handle case where there are no more foods
            setPlaceholderProperties(holder);
        }
    }

    private void bindObjectItem(YourOfferAdapter.MyViewHolder holder) {
        if (objectCounter < yourSelectedItems.getObjectLists().size()) {
            ObjectModel objectModel = yourSelectedItems.getObjectLists().get(objectCounter);
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

    private void setItemProperties(YourOfferAdapter.MyViewHolder holder, int resourceId, int rarityColor, int typeColor) {
        holder.offererItemImage.setImageResource(resourceId);
        holder.offererItemImageContainer.setBackgroundColor(rarityColor);
        holder.offererItemImage.setBackgroundColor(typeColor);
    }

    private void setPlaceholderProperties(YourOfferAdapter.MyViewHolder holder) {
        int placeholderResourceId = getResourceId("icon_blank"); // Assuming there's a placeholder image
        int placeholderColor = getColor(R.color.common);

        holder.offererItemImage.setImageResource(placeholderResourceId);
        holder.offererItemImageContainer.setBackgroundColor(placeholderColor);
        holder.offererItemImage.setBackgroundColor(placeholderColor);
    }

    private void resetCounters() {
        petCounter = 0;
        eggCounter = 0;
        foodCounter = 0;
        objectCounter = 0;
    }

    private void resetOffer()
    {
        this.yourSelectedItems.addPetLists(new PetModel("add"));
        this.itemSeries.add(0);
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
