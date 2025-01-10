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

import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<PetModel> petModels;

    public PetAdapter(Activity activity, ArrayList<PetModel> petModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.petModels = petModels;
    }

    @NonNull
    @Override
    public PetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pet_inventory, parent, false);
        return new PetAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.MyViewHolder holder, int position) {
        PetModel petModel = petModels.get(position);
        int resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

        holder.petImage.setImageResource(resourceId);
        holder.petName.setText(petModels.get(position).getPetName());
        holder.petCardView.setBackgroundResource(petModels.get(position).getRarityBackground());
        holder.petImageContainer.setBackgroundResource(petModels.get(position).getTypeBackground());

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
        ImageView rarityColor = dialog.findViewById(R.id.rarityColor), typeColor = dialog.findViewById(R.id.typeColor);

        PetModel petModel = petModels.get(holder.getBindingAdapterPosition());
        int resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

        petImage.setImageResource(resourceId);
        petImage.setBackgroundResource(petModels.get(holder.getBindingAdapterPosition()).getRarityBackground());
        petName.setText(petModels.get(holder.getBindingAdapterPosition()).getPetName());
        progressDetail.setText(petModels.get(holder.getBindingAdapterPosition()).getExp() + "/" + petModels.get(holder.getBindingAdapterPosition()).getMaxExp());
        rarity.setText(petModels.get(holder.getBindingAdapterPosition()).getRarity());
        age.setText(petModels.get(holder.getBindingAdapterPosition()).ageToString() + "");
        type.setText(petModels.get(holder.getBindingAdapterPosition()).typeToString() + "");
        expBar.setMax(petModels.get(holder.getBindingAdapterPosition()).getMaxExp());
        expBar.setProgress(petModels.get(holder.getBindingAdapterPosition()).getExp());

        rarityColor.setBackgroundColor(ContextCompat.getColor(context, petModels.get(holder.getBindingAdapterPosition()).getRarityColor()));
        typeColor.setBackgroundColor(ContextCompat.getColor(context, petModels.get(holder.getBindingAdapterPosition()).getTypeColor()));

        dialog.show();
    }
}
