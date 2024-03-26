package com.example.miwipet.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

import java.util.ArrayList;

public class EggSelectionAdapter extends RecyclerView.Adapter<EggSelectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<EggModel> eggModels;

    public EggSelectionAdapter(Context context, ArrayList<EggModel> eggModels)
    {
        this.context = context;
        this.eggModels = eggModels;
    }

    @NonNull
    @Override
    public EggSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.egg_selection, parent, false);
        return new EggSelectionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EggSelectionAdapter.MyViewHolder holder, int position) {
        holder.eggImage.setImageResource(eggModels.get(position).getEggImage());
        holder.eggName.setText(eggModels.get(position).getEggName());
        holder.eggQuantity.setText("x1");
    }

    @Override
    public int getItemCount() {
        return eggModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView eggImage;
        TextView eggName, eggQuantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            eggImage = itemView.findViewById(R.id.eggImage);
            eggName = itemView.findViewById(R.id.eggName);
            eggQuantity = itemView.findViewById(R.id.eggQuantity);
        }
    }
}
