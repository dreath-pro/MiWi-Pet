package com.example.miwipet.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.models.ObjectModel;

import java.util.ArrayList;

public class ObjectAdapter extends RecyclerView.Adapter<ObjectAdapter.MyViewHolder> {
    private Context context;
    private Activity activity;
    private ArrayList<ObjectModel> objectModels;

    public ObjectAdapter(Activity activity, ArrayList<ObjectModel> objectModels) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.objectModels = objectModels;
    }

    @NonNull
    @Override
    public ObjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.object_inventory, parent, false);
        return new ObjectAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectAdapter.MyViewHolder holder, int position) {
        int resourceId = context.getResources().getIdentifier(objectModels.get(position).getObjectImage(), "drawable", context.getPackageName());

        holder.objectImage.setImageResource(resourceId);
        holder.objectName.setText(objectModels.get(position).getObjectName());
        holder.objectCardView.setBackgroundResource(objectModels.get(position).getRarityBackground());
    }

    @Override
    public int getItemCount() {
        return objectModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView objectImage;
        TextView objectName;
        CardView objectCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            objectImage = itemView.findViewById(R.id.objectImage);
            objectName = itemView.findViewById(R.id.objectName);
            objectCardView = itemView.findViewById(R.id.objectCardView);
        }
    }
}
