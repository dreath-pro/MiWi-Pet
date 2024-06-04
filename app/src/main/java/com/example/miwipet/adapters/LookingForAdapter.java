package com.example.miwipet.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.database.FoodDatabase;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.utils.RefreshInventory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import android.os.Handler;
import android.os.Looper;

public class LookingForAdapter extends RecyclerView.Adapter<LookingForAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<OfferModel> offerModels;
    private InventoryModel yourInventory;
    private TheirOfferAdapter theirOfferAdapter;

    private RefreshInventory refreshInventory;
    private PetDatabase petDatabase;
    private EggDatabase eggDatabase;
    private FoodDatabase foodDatabase;

    private Handler handler = new Handler();

    Button refreshOfferButton;

    public LookingForAdapter(Activity activity, ArrayList<OfferModel> offerModels, InventoryModel yourInventory, Button refreshOfferButton) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.offerModels = offerModels;
        this.yourInventory = yourInventory;
        this.refreshOfferButton = refreshOfferButton;

        petDatabase = new PetDatabase(context);
        eggDatabase = new EggDatabase(context);
        foodDatabase = new FoodDatabase(context);
        refreshInventory = new RefreshInventory(context, yourInventory);
    }

    @NonNull
    @Override
    public LookingForAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.looking_for_view, parent, false);
        return new LookingForAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LookingForAdapter.MyViewHolder holder, int position) {
        int rarityColor = 0;
        int typeColor = 0;
        int resourceId = 0;

        switch (offerModels.get(position).getWantItemSingle()) {
            case 0:
                PetModel petModel = offerModels.get(position).getWantItem().getPetLists().get(0);
                resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

                holder.wantName.setText(offerModels.get(position).getWantItem().getPetLists().get(0).getPetName());

                rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getRarityColor());
                typeColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getTypeColor());
                break;
            case 1:
                EggModel eggModel = offerModels.get(position).getWantItem().getEggLists().get(0);
                resourceId = context.getResources().getIdentifier(eggModel.getEggImage(), "drawable", context.getPackageName());

                holder.wantName.setText(offerModels.get(position).getWantItem().getEggLists().get(0).getEggName());

                rarityColor = ContextCompat.getColor(context, R.color.common);
                typeColor = ContextCompat.getColor(context, R.color.white);
                break;
            case 2:
                FoodModel foodModel = offerModels.get(position).getWantItem().getFoodLists().get(0);
                resourceId = context.getResources().getIdentifier(foodModel.getFoodImage(), "drawable", context.getPackageName());

                holder.wantName.setText(offerModels.get(position).getWantItem().getFoodLists().get(0).getFoodName());

                rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getFoodLists().get(0).getRarityColor());
                typeColor = ContextCompat.getColor(context, R.color.white);
                break;
        }

        holder.wantImage.setImageResource(resourceId);
        holder.wantImageContainer.setBackgroundColor(rarityColor);
        holder.wantImage.setBackgroundColor(typeColor);

        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails(holder);
            }
        });

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theirTradeDetails(holder);
            }
        });

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Accepted Offer", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return offerModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView userIcon;
        ImageView wantImage;
        TextView wantName;
        Button viewButton;
        Button acceptButton;
        FrameLayout wantImageContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            userIcon = itemView.findViewById(R.id.userIcon);
            wantImage = itemView.findViewById(R.id.wantImage);
            wantName = itemView.findViewById(R.id.wantName);
            viewButton = itemView.findViewById(R.id.viewButton);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            wantImageContainer = itemView.findViewById(R.id.wantImageContainer);
        }
    }

    private void theirTradeDetails(LookingForAdapter.MyViewHolder holder) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.their_trade_details);

        TextView yourName = dialog.findViewById(R.id.yourName);
        TextView offererName = dialog.findViewById(R.id.offererName);
        LinearLayout wantImageContainer = dialog.findViewById(R.id.wantImageContainer);
        ImageView wantImage = dialog.findViewById(R.id.wantImage);
        RecyclerView theirOfferView = dialog.findViewById(R.id.theirOfferView);
        Button acceptButton = dialog.findViewById(R.id.acceptButton);
        Button declineButton = dialog.findViewById(R.id.declineButton);
        ImageView offererReady = dialog.findViewById(R.id.offererReady);

        OfferModel offerModel = offerModels.get(holder.getBindingAdapterPosition());
        int wantResourceId = 0;
        int wantRarity = 0;
        int wantType = 0;

        offererReady.setVisibility(View.INVISIBLE);

        switch (offerModel.getWantItemSingle()) {
            case 0:
                wantResourceId = context.getResources().getIdentifier(offerModel.getWantItem().getPetLists().get(0).getPetImage(), "drawable", context.getPackageName());
                wantRarity = ContextCompat.getColor(context, offerModel.getWantItem().getPetLists().get(0).getRarityColor());
                wantType = ContextCompat.getColor(context, offerModel.getWantItem().getPetLists().get(0).getTypeColor());
                break;
            case 1:
                wantResourceId = context.getResources().getIdentifier(offerModel.getWantItem().getEggLists().get(0).getEggImage(), "drawable", context.getPackageName());
                wantRarity = ContextCompat.getColor(context, R.color.white);
                wantType = ContextCompat.getColor(context, R.color.common);
                break;
            case 2:
                wantResourceId = context.getResources().getIdentifier(offerModel.getWantItem().getFoodLists().get(0).getFoodImage(), "drawable", context.getPackageName());
                wantRarity = ContextCompat.getColor(context, offerModel.getWantItem().getFoodLists().get(0).getRarityColor());
                wantType = ContextCompat.getColor(context, R.color.white);
                break;
        }

        yourName.setText("You");
        offererName.setText(offerModel.getUsername());
        wantImage.setImageResource(wantResourceId);
        wantImageContainer.setBackgroundColor(wantRarity);
        wantImage.setBackgroundColor(wantType);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        theirOfferView.setLayoutManager(gridLayoutManager);
        theirOfferAdapter = new TheirOfferAdapter(activity, offerModels.get(holder.getBindingAdapterPosition()).getOffererItemSeries(), offerModels.get(holder.getBindingAdapterPosition()).getOffererItem());
        theirOfferView.setAdapter(theirOfferAdapter);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean doesItemExist = false;

                switch (offerModel.getWantItemSingle())
                {
                    case 0:
                        for(int i = 0; i <= yourInventory.getPetLists().size() - 1; i++)
                        {
                            if(yourInventory.getPetLists().get(i).getPetName().equals(offerModel.getWantItem().getPetLists().get(0).getPetName()))
                            {
                                if(yourInventory.getPetLists().get(i).getType() == offerModel.getWantItem().getPetLists().get(0).getType())
                                {
                                    ArrayList<PetModel> temporaryPetModels = yourInventory.getPetLists();
                                    temporaryPetModels.remove(i);
                                    petDatabase.clearPet();
                                    for(PetModel petModel : temporaryPetModels)
                                    {
                                        petDatabase.addPet(petModel);
                                    }
                                    refreshInventory.getPetFromDatabase();

                                    getAllOffers(offerModel);
                                    doesItemExist = true;

                                    break;
                                }
                            }
                        }
                        break;
                    case 1:
                        for(int i = 0; i <= yourInventory.getEggLists().size() - 1; i++)
                        {
                            if(yourInventory.getEggLists().get(i).getEggName().equals(offerModel.getWantItem().getEggLists().get(0).getEggName()))
                            {
                                ArrayList<EggModel> temporaryEggModels = yourInventory.getEggLists();
                                temporaryEggModels.remove(i);
                                eggDatabase.clearEgg();
                                for(EggModel eggModel : temporaryEggModels)
                                {
                                    eggDatabase.addEgg(eggModel);
                                }
                                refreshInventory.getEggFromDatabase();

                                getAllOffers(offerModel);
                                doesItemExist = true;

                                break;
                            }
                        }
                        break;
                    case 2:
                        for(int i = 0; i <= yourInventory.getFoodLists().size() - 1; i++)
                        {
                            if(yourInventory.getFoodLists().get(i).getFoodName().equals(offerModel.getWantItem().getFoodLists().get(0).getFoodName()))
                            {
                                ArrayList<FoodModel> temporaryFoodModels = yourInventory.getFoodLists();
                                temporaryFoodModels.remove(i);
                                foodDatabase.clearFood();
                                for(FoodModel foodModel : temporaryFoodModels)
                                {
                                    foodDatabase.addFood(foodModel);
                                }
                                refreshInventory.getFoodFromDatabase();

                                getAllOffers(offerModel);
                                doesItemExist = true;

                                break;
                            }
                        }
                        break;
                }

                if(doesItemExist)
                {
                    acceptButton.setEnabled(false);
                    offererReady.setVisibility(View.VISIBLE);
                    startCloseDelay(dialog);
                    refreshOfferButton.performClick();
                }else
                {
                    Toast.makeText(context, "Requirements not met!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void startCloseDelay(Dialog dialog)
    {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        }, 1800);
    }

    private void getAllOffers(OfferModel offerModel)
    {
        for(PetModel theirPets : offerModel.getOffererItem().getPetLists())
        {
            petDatabase.addPet(theirPets);
        }

        for(EggModel theirEggs : offerModel.getOffererItem().getEggLists())
        {
            eggDatabase.addEgg(theirEggs);
        }

        for(FoodModel theirFoods : offerModel.getOffererItem().getFoodLists())
        {
            foodDatabase.addFood(theirFoods);
        }

        refreshInventory.getPetFromDatabase();
        refreshInventory.getEggFromDatabase();
        refreshInventory.getFoodFromDatabase();
    }

    private void profileDetails(LookingForAdapter.MyViewHolder holder) {
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
