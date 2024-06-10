package com.example.miwipet.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.database.FoodDatabase;
import com.example.miwipet.database.ObjectDatabase;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.utils.RefreshInventory;

import java.util.ArrayList;
import java.util.Random;

public class ForTradeAdapter extends RecyclerView.Adapter<ForTradeAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<OfferModel> offerModels;
    private InventoryModel yourInventory;
    private TheirOfferAdapter theirOfferAdapter;

    private RefreshInventory refreshInventory;
    private PetDatabase petDatabase;
    private EggDatabase eggDatabase;
    private FoodDatabase foodDatabase;
    private ObjectDatabase objectDatabase;

    private Handler handler = new Handler();

    Button refreshOfferButton;

    public ForTradeAdapter(Activity activity, ArrayList<OfferModel> offerModels, InventoryModel yourInventory, Button refreshOfferButton) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.offerModels = offerModels;
        this.yourInventory = yourInventory;
        this.refreshOfferButton = refreshOfferButton;

        petDatabase = new PetDatabase(context);
        eggDatabase = new EggDatabase(context);
        foodDatabase = new FoodDatabase(context);
        objectDatabase = new ObjectDatabase(context);
        refreshInventory = new RefreshInventory(context, yourInventory);
    }

    @NonNull
    @Override
    public ForTradeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.for_trade_view, parent, false);
        return new ForTradeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForTradeAdapter.MyViewHolder holder, int position) {
        int rarityColor = 0;
        int typeColor = 0;
        int resourceId = 0;

        switch (offerModels.get(position).getWantItemSingle()) {
            case 0:
                PetModel petModel = offerModels.get(position).getWantItem().getPetLists().get(0);
                resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

                holder.forTradeName.setText(offerModels.get(position).getWantItem().getPetLists().get(0).getPetName());

                rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getRarityColor());
                typeColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getTypeColor());
                break;
            case 1:
                EggModel eggModel = offerModels.get(position).getWantItem().getEggLists().get(0);
                resourceId = context.getResources().getIdentifier(eggModel.getEggImage(), "drawable", context.getPackageName());

                holder.forTradeName.setText(offerModels.get(position).getWantItem().getEggLists().get(0).getEggName());

                rarityColor = ContextCompat.getColor(context, R.color.common);
                typeColor = ContextCompat.getColor(context, R.color.egg);
                break;
            case 2:
                FoodModel foodModel = offerModels.get(position).getWantItem().getFoodLists().get(0);
                resourceId = context.getResources().getIdentifier(foodModel.getFoodImage(), "drawable", context.getPackageName());

                holder.forTradeName.setText(offerModels.get(position).getWantItem().getFoodLists().get(0).getFoodName());

                rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getFoodLists().get(0).getRarityColor());
                typeColor = ContextCompat.getColor(context, R.color.food);
                break;
            case 3:
                ObjectModel objectModel = offerModels.get(position).getWantItem().getObjectLists().get(0);
                resourceId = context.getResources().getIdentifier(objectModel.getObjectImage(), "drawable", context.getPackageName());

                holder.forTradeName.setText(offerModels.get(position).getWantItem().getObjectLists().get(0).getObjectName());

                rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getObjectLists().get(0).getRarityColor());
                typeColor = ContextCompat.getColor(context, R.color.object);
                break;
        }

        holder.forTradeImage.setImageResource(resourceId);
        holder.forTradeImageContainer.setBackgroundColor(rarityColor);
        holder.forTradeImage.setBackgroundColor(typeColor);

        holder.makeOfferButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Otw na!", Toast.LENGTH_SHORT).show();
            }
        });
        
        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails(holder);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userIcon;
        ImageView forTradeImage;
        FrameLayout forTradeImageContainer;
        TextView forTradeName;
        Button makeOfferButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userIcon = itemView.findViewById(R.id.userIcon);
            forTradeImage = itemView.findViewById(R.id.forTradeImage);
            forTradeImageContainer = itemView.findViewById(R.id.forTradeImageContainer);
            forTradeName = itemView.findViewById(R.id.forTradeName);
            makeOfferButton = itemView.findViewById(R.id.makeOfferButton);
        }
    }

    private void profileDetails(ForTradeAdapter.MyViewHolder holder) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.profile_details);

        ImageView userProfile = dialog.findViewById(R.id.userProfile);
        TextView username = dialog.findViewById(R.id.username);
        ImageView status = dialog.findViewById(R.id.status);
        TextView successTradeText = dialog.findViewById(R.id.successTradeText);
        TextView failedTradeText = dialog.findViewById(R.id.failedTradeText);

        OfferModel offerModel = offerModels.get(holder.getBindingAdapterPosition());
        int userResourceId = context.getResources().getIdentifier(offerModel.getUserImage(), "drawable", context.getPackageName());

        Random random = new Random();
        int statusResourceId = 0;
        int selectedStatus = random.nextInt(3);

        switch (selectedStatus) {
            case 0:
                statusResourceId = context.getResources().getIdentifier("status_active", "drawable", context.getPackageName());
                break;
            case 1:
                statusResourceId = context.getResources().getIdentifier("status_away", "drawable", context.getPackageName());
                break;
            case 2:
                statusResourceId = context.getResources().getIdentifier("status_do_not_disturb", "drawable", context.getPackageName());
                break;
        }

        userProfile.setImageResource(userResourceId);
        username.setText(offerModel.getUsername());
        status.setImageResource(statusResourceId);
        successTradeText.setText("✅ Success Trade: " + offerModel.getSuccessTrade());
        failedTradeText.setText("❌ Failed Trade: " + offerModel.getFailedTrade());

        dialog.show();
    }
}
