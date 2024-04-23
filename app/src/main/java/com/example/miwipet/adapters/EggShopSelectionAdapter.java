package com.example.miwipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

import java.util.ArrayList;

public class EggShopSelectionAdapter extends RecyclerView.Adapter<EggShopSelectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<EggModel> eggModels;

    public EggShopSelectionAdapter(Context context, ArrayList<EggModel> eggModels) {
        this.context = context;
        this.eggModels = eggModels;
    }

    @NonNull
    @Override
    public EggShopSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.egg_shop_selection, parent, false);
        return new EggShopSelectionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EggShopSelectionAdapter.MyViewHolder holder, int position) {
        holder.eggImage.setImageResource(eggModels.get(position).getEggImage());
        holder.eggName.setText(eggModels.get(position).getEggName());
        holder.chipPrice.setText(eggModels.get(position).getChipPrice() + "");
        holder.glazePrice.setText(eggModels.get(position).getGlazePrice() + "");

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Haha Buy", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return eggModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView eggImage;
        TextView eggName;
        TextView chipPrice, glazePrice;
        Button buyButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eggImage = itemView.findViewById(R.id.eggImage);
            eggName = itemView.findViewById(R.id.eggName);
            chipPrice = itemView.findViewById(R.id.chipPrice);
            glazePrice = itemView.findViewById(R.id.glazePrice);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
