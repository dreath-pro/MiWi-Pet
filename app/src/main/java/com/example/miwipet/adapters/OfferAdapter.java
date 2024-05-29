package com.example.miwipet.adapters;

import android.app.Activity;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.OfferModel;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<OfferModel> offerModels;

    public OfferAdapter(Activity activity, ArrayList<OfferModel> offerModels)
    {
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
        PetModel petModel = offerModels.get(position).getWantItem().getPetLists().get(0);
        int resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

        holder.wantImage.setImageResource(resourceId);
        holder.wantName.setText(offerModels.get(position).getWantItem().getPetLists().get(0).getPetName());
        holder.petCardView.setBackgroundResource(offerModels.get(position).getWantItem().getPetLists().get(0).getRarityColor());
        holder.wantImageContainer.setBackgroundResource(offerModels.get(position).getWantItem().getPetLists().get(0).getTypeColor());

        holder.userIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Viewed Profile", Toast.LENGTH_SHORT).show();
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

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView userIcon;
        ImageView wantImage;
        TextView wantName;
        Button viewButton;
        Button acceptButton;
        FrameLayout wantImageContainer;
        CardView petCardView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userIcon = itemView.findViewById(R.id.userIcon);
            wantImage = itemView.findViewById(R.id.wantImage);
            wantName = itemView.findViewById(R.id.wantName);
            viewButton = itemView.findViewById(R.id.viewButton);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            wantImageContainer = itemView.findViewById(R.id.wantImageContainer);
            petCardView = itemView.findViewById(R.id.petCardView);
        }
    }
}
