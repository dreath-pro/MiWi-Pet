package com.example.miwipet.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.utils.Rarity;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<PetModel> petModels;
    private Rarity rarity = new Rarity();
    private String[] rarities = new String[]{
            rarity.getRarity(0),
            rarity.getRarity(1),
            rarity.getRarity(2),
            rarity.getRarity(3),
            rarity.getRarity(4)};

    public InventoryAdapter(Activity activity, ArrayList<PetModel> petModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
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

        if (petModels.get(position).getRarity().equals(rarities[0])) {
            holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, rarity.getRarityColor(0)));
        } else if (petModels.get(position).getRarity().equals(rarities[1])) {
            holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, rarity.getRarityColor(1)));
        } else if (petModels.get(position).getRarity().equals(rarities[2])) {
            holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, rarity.getRarityColor(2)));
        } else if (petModels.get(position).getRarity().equals(rarities[3])) {
            holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, rarity.getRarityColor(3)));
        } else if (petModels.get(position).getRarity().equals(rarities[4])) {
            holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, rarity.getRarityColor(4)));
        }

        holder.petImageContainer.setBackgroundColor(ContextCompat.getColor(context, rarity.getTypeColor(petModels.get(position).getType())));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventoryDetails(holder);
            }
        });
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

    private void inventoryDetails(InventoryAdapter.MyViewHolder holder) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.pet_inventory_details);

        ImageView petImage = dialog.findViewById(R.id.petImage);
        TextView petName = dialog.findViewById(R.id.petName),
                progressDetail = dialog.findViewById(R.id.progressDetail),
                name = dialog.findViewById(R.id.name),
                rarity = dialog.findViewById(R.id.rarity),
                age = dialog.findViewById(R.id.age),
                type = dialog.findViewById(R.id.type);
        ProgressBar expBar = dialog.findViewById(R.id.expBar);
        ImageView rarityColor = dialog.findViewById(R.id.rarityColor),
                typeColor = dialog.findViewById(R.id.typeColor);

        petImage.setImageResource(petModels.get(holder.getAdapterPosition()).getPetImage());
        petName.setText(petModels.get(holder.getAdapterPosition()).getPetName());
        progressDetail.setText(petModels.get(holder.getAdapterPosition()).getExp() + "/" +
                petModels.get(holder.getAdapterPosition()).getMaxExp());
        name.setText(petModels.get(holder.getAdapterPosition()).getPetName());
        rarity.setText(petModels.get(holder.getAdapterPosition()).getRarity());
        age.setText(petModels.get(holder.getAdapterPosition()).getAge() + "");
        type.setText(petModels.get(holder.getAdapterPosition()).getType() + "");
        expBar.setMax(petModels.get(holder.getAdapterPosition()).getMaxExp());
        expBar.setProgress(petModels.get(holder.getAdapterPosition()).getExp());

        if (petModels.get(holder.getAdapterPosition()).getRarity().equals(rarities[0])) {
            rarityColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getRarityColor(0)));
        } else if (petModels.get(holder.getAdapterPosition()).getRarity().equals(rarities[1])) {
            rarityColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getRarityColor(1)));
        } else if (petModels.get(holder.getAdapterPosition()).getRarity().equals(rarities[2])) {
            rarityColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getRarityColor(2)));
        } else if (petModels.get(holder.getAdapterPosition()).getRarity().equals(rarities[3])) {
            rarityColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getRarityColor(3)));
        } else if (petModels.get(holder.getAdapterPosition()).getRarity().equals(rarities[4])) {
            rarityColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getRarityColor(4)));
        }

        typeColor.setBackgroundColor(ContextCompat.getColor(context, this.rarity.getTypeColor(petModels.get(holder.getAdapterPosition()).getType())));

        dialog.show();
    }
}
