package com.example.miwipet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.database.CurrencyDatabase;
import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.logics.EggSource;
import com.example.miwipet.logics.RefreshInventory;

import java.util.ArrayList;

public class EggShopSelectionAdapter extends RecyclerView.Adapter<EggShopSelectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<EggModel> displayEggs;
    private InventoryModel inventoryModel;
    private TextView chipToken, glazeToken;

    private RefreshInventory refreshInventory;
    private EggSource eggSource;

    public EggShopSelectionAdapter(
            Context context,
            EggSource eggSource,
            ArrayList<EggModel> displayEggs,
            TextView chipToken,
            TextView glazeToken,
            InventoryModel inventoryModel) {
        this.context = context;
        this.eggSource = eggSource;
        this.displayEggs = displayEggs;
        this.chipToken = chipToken;
        this.glazeToken = glazeToken;
        this.inventoryModel = inventoryModel;

        refreshInventory = new RefreshInventory(context, inventoryModel);
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
        EggDatabase eggDatabase = new EggDatabase(context);
        CurrencyDatabase currencyDatabase = new CurrencyDatabase(context);
        int resourceId = context.getResources().getIdentifier(displayEggs.get(position).getEggImage(), "drawable", context.getPackageName());

        holder.eggImage.setImageResource(resourceId);
        holder.eggName.setText(displayEggs.get(position).getEggName());
        holder.chipPrice.setText(displayEggs.get(position).getChipPrice() + "");
        holder.glazePrice.setText(displayEggs.get(position).getGlazePrice() + "");

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inventoryModel.getChipToken() >=
                        displayEggs.get(holder.getBindingAdapterPosition()).getChipPrice() &&
                        inventoryModel.getGlazeToken() >=
                                displayEggs.get(holder.getBindingAdapterPosition()).getGlazePrice()) {

                    eggDatabase.addEgg(eggSource.getEggByString(holder.eggName.getText().toString()));
                    refreshInventory.refreshInventory();

                    inventoryModel.setChipToken(inventoryModel.getChipToken() -
                            displayEggs.get(holder.getBindingAdapterPosition()).getChipPrice());

                    inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() -
                            displayEggs.get(holder.getBindingAdapterPosition()).getGlazePrice());

                    currencyDatabase.updateToken(inventoryModel);

                    chipToken.setText(inventoryModel.getChipToken() + "");
                    glazeToken.setText(inventoryModel.getGlazeToken() + "");

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayEggs.size();
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
