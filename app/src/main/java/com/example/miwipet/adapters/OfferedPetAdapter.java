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

public class OfferedPetAdapter extends RecyclerView.Adapter<OfferedPetAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<PetModel> petModels;
    private OnPetSelectedListener onPetSelectedListener;

    public interface OnPetSelectedListener {
        void onPetSelected(PetModel petModel);
    }

    public OfferedPetAdapter(Activity activity, ArrayList<PetModel> petModels, OnPetSelectedListener onPetSelectedListener) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.petModels = petModels;
        this.onPetSelectedListener = onPetSelectedListener;
    }

    @NonNull
    @Override
    public OfferedPetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.pet_inventory, parent, false);
        return new OfferedPetAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PetModel petModel = petModels.get(position);
        int resourceId = context.getResources().getIdentifier(petModel.getPetImage(), "drawable", context.getPackageName());

        holder.petImage.setImageResource(resourceId);
        holder.petName.setText(petModels.get(position).getPetName());
        holder.petCardView.setBackgroundResource(petModels.get(position).getRarityBackground());
        holder.petImageContainer.setBackgroundResource(petModels.get(position).getTypeBackground());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onPetSelectedListener != null)
                {
                    onPetSelectedListener.onPetSelected(petModel);
                }
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
}
