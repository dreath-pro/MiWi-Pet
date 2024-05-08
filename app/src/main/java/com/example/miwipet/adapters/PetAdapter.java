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

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.utils.Rarity;

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.MyViewHolder> {
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

    public PetAdapter(Activity activity, ArrayList<PetModel> petModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.petModels = petModels;
    }

    @NonNull
    @Override
    public PetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.inventory_selection, parent, false);
        return new PetAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.MyViewHolder holder, int position) {
        holder.petImage.setImageResource(petModels.get(position).getPetImage());
        holder.petName.setText(petModels.get(position).getPetName());
        holder.petCardView.setCardBackgroundColor(ContextCompat.getColor(context, petModels.get(position).getRarityColor()));
        holder.petImageContainer.setBackgroundColor(ContextCompat.getColor(context, petModels.get(position).getTypeColor()));

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

    private void inventoryDetails(PetAdapter.MyViewHolder holder) {
        Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.pet_inventory_details);

        ImageView petImage = dialog.findViewById(R.id.petImage);
        TextView petName = dialog.findViewById(R.id.petName),
                progressDetail = dialog.findViewById(R.id.progressDetail),
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
        rarity.setText(petModels.get(holder.getAdapterPosition()).getRarity());
        age.setText(petModels.get(holder.getAdapterPosition()).ageToString() + "");
        type.setText(petModels.get(holder.getAdapterPosition()).typeToString() + "");
        expBar.setMax(petModels.get(holder.getAdapterPosition()).getMaxExp());
        expBar.setProgress(petModels.get(holder.getAdapterPosition()).getExp());

        rarityColor.setBackgroundColor(ContextCompat.getColor(context, petModels.get(holder.getAdapterPosition()).getRarityColor()));
        typeColor.setBackgroundColor(ContextCompat.getColor(context, petModels.get(holder.getAdapterPosition()).getTypeColor()));

        dialog.show();
    }
}
