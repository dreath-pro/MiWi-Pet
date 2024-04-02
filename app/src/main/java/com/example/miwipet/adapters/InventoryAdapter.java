package com.example.miwipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.PetModel;

import java.util.ArrayList;
import java.util.Random;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<PetModel> petModels;

    public InventoryAdapter(Context context, ArrayList<PetModel> petModels) {
        this.context = context;
        this.petModels = petModels;
    }

    @NonNull
    @Override
    public InventoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inventory_selection, parent, false);
        return new InventoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryAdapter.MyViewHolder holder, int position) {
        holder.petImage.setImageResource(petModels.get(position).getPetImage());
        holder.petName.setText(petModels.get(position).getPetName());

        switch (petModels.get(position).getRarity())
        {
            case "Common":
                holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.common));
                break;
            case "Uncommon":
                holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.uncommon));
                break;
            case "Rare":
                holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.rare));
                break;
            case "Legendary":
                holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.legendary));
                break;
            case "Mythic":
                holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.mythic));
                break;
        }

        switch (petModels.get(position).getType())
        {
            case 0:
                holder.petImageContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.normal));
                break;
            case 1:
                holder.petImageContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.crystal));
                break;
            case 2:
                holder.petImageContainer.setBackgroundColor(ContextCompat.getColor(context, R.color.gemstone));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return petModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        FrameLayout petImageContainer;
        TextView petName;
        CardView petCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            petImage = itemView.findViewById(R.id.petImage);
            petName = itemView.findViewById(R.id.petName);
            petCardView = itemView.findViewById(R.id.petCardView);
            petImageContainer = itemView.findViewById(R.id.petImageContainer);
        }
    }
}
