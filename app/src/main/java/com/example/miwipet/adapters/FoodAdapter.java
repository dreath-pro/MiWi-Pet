package com.example.miwipet.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.utils.Rarity;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<FoodModel> foodModels;

    public FoodAdapter(Activity activity, ArrayList<FoodModel> foodModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.foodModels = foodModels;
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_inventory, parent, false);
        return new FoodAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        holder.foodImage.setImageResource(foodModels.get(position).getFoodImage());
        holder.foodName.setText(foodModels.get(position).getFoodName());
        holder.foodCardView.setBackgroundResource(foodModels.get(position).getRarityBackground());
    }

    @Override
    public int getItemCount() {
        return foodModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView foodImage;
        TextView foodName;
        CardView foodCardView;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodCardView = itemView.findViewById(R.id.foodCardView);
        }
    }
}
