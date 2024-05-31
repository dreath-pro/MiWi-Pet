package com.example.miwipet.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;
import java.util.Random;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<OfferModel> offerModels;

    public OfferAdapter(Activity activity, ArrayList<OfferModel> offerModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.offerModels = offerModels;
    }

    @NonNull
    @Override
    public OfferAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.their_offer, parent, false);
        return new OfferAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferAdapter.MyViewHolder holder, int position) {
        switch (offerModels.get(position).getWantItemSingle()) {
            case 0:
                PetModel petModel = offerModels.get(position).getWantItem().getPetLists().get(0);
                int resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

                holder.wantImage.setImageResource(resourceId);
                holder.wantName.setText(offerModels.get(position).getWantItem().getPetLists().get(0).getPetName());

                int rarityColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getRarityColor());
                int typeColor = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getPetLists().get(0).getTypeColor());

                holder.wantImageContainer.setBackgroundColor(rarityColor);
                holder.wantImage.setBackgroundColor(typeColor);
                break;
            case 1:
                EggModel eggModel = offerModels.get(position).getWantItem().getEggLists().get(0);
                int resourceId2 = context.getResources().getIdentifier(eggModel.getEggImage(), "drawable", context.getPackageName());

                holder.wantImage.setImageResource(resourceId2);
                holder.wantName.setText(offerModels.get(position).getWantItem().getEggLists().get(0).getEggName());

                int imageColor = ContextCompat.getColor(context, R.color.common);
                int backgroundColor = ContextCompat.getColor(context, R.color.white);

                holder.wantImageContainer.setBackgroundColor(backgroundColor);
                holder.wantImage.setBackgroundColor(imageColor);
                break;
            case 2:
                FoodModel foodModel = offerModels.get(position).getWantItem().getFoodLists().get(0);
                int resourceId3 = context.getResources().getIdentifier(foodModel.getFoodImage(), "drawable", context.getPackageName());

                holder.wantImage.setImageResource(resourceId3);
                holder.wantName.setText(offerModels.get(position).getWantItem().getFoodLists().get(0).getFoodName());

                int rarityColor2 = ContextCompat.getColor(context, offerModels.get(position).getWantItem().getFoodLists().get(0).getRarityColor());
                int typeColor2 = ContextCompat.getColor(context, R.color.white);

                holder.wantImageContainer.setBackgroundColor(rarityColor2);
                holder.wantImage.setBackgroundColor(typeColor2);
                break;
        }

        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails(holder);
            }
        });

        holder.viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Viewed Offer", Toast.LENGTH_SHORT).show();
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

    private void profileDetails(OfferAdapter.MyViewHolder holder)
    {
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

        switch (selectedStatus)
        {
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
