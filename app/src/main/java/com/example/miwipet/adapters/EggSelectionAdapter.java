package com.example.miwipet.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.activities.MainActivity;
import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;

import java.util.ArrayList;

public class EggSelectionAdapter extends RecyclerView.Adapter<EggSelectionAdapter.MyViewHolder> {
    private Context context;
    private InventoryModel inventoryModels;
    private ArrayList<EggModel> incubated;
    private FragmentManager fragmentManager;

    ImageView eggImage;

    public EggSelectionAdapter(Context context, InventoryModel inventoryModels, ArrayList<EggModel> incubated,
                               FragmentManager fragmentManager, ImageView eggImage) {
        this.context = context;
        this.inventoryModels = inventoryModels;
        this.fragmentManager = fragmentManager;
        this.eggImage = eggImage;
        this.incubated = incubated;
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
        EggDatabase eggDatabase = new EggDatabase(context);
        int resourceId = context.getResources().getIdentifier(inventoryModels.getEggLists().get(position).getEggImage(), "drawable", context.getPackageName());

        holder.eggImage.setImageResource(resourceId);
        holder.eggName.setText(inventoryModels.getEggLists().get(position).getEggName());
        holder.eggQuantity.setText("x1");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resourceId = context.getResources().getIdentifier(inventoryModels.getEggLists().get(holder.getAdapterPosition()).getEggImage(), "drawable", context.getPackageName());
                eggImage.setImageResource(resourceId);

                for(EggModel eggModel : inventoryModels.getEggLists())
                {
                    eggModel.setSelected(false);
                }
                inventoryModels.getEggLists().get(holder.getBindingAdapterPosition()).setSelected(true);

                eggDatabase.deleteEgg(inventoryModels.getEggLists().get(holder.getBindingAdapterPosition()));
                incubated.add(inventoryModels.getEggLists().get(holder.getBindingAdapterPosition()));
                inventoryModels.getEggLists().remove(holder.getBindingAdapterPosition());

                if (fragmentManager != null) {
                    fragmentManager.popBackStack();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return inventoryModels.getEggLists().size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
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
