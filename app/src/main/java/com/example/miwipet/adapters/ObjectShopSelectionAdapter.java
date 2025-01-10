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
import com.example.miwipet.database.ObjectDatabase;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.logics.ObjectSource;
import com.example.miwipet.logics.RefreshInventory;

import java.util.ArrayList;

public class ObjectShopSelectionAdapter extends RecyclerView.Adapter<ObjectShopSelectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ObjectModel> displayObjects;
    private InventoryModel inventoryModel;
    private TextView chipToken, glazeToken;

    private RefreshInventory refreshInventory;
    private ObjectSource objectSource;

    public ObjectShopSelectionAdapter(
            Context context,
            ObjectSource objectSource,
            ArrayList<ObjectModel> displayObjects,
            TextView chipToken,
            TextView glazeToken,
            InventoryModel inventoryModel) {
        this.context = context;
        this.objectSource = objectSource;
        this.displayObjects = displayObjects;
        this.chipToken = chipToken;
        this.glazeToken = glazeToken;
        this.inventoryModel = inventoryModel;

        refreshInventory = new RefreshInventory(context, inventoryModel);
    }

    @NonNull
    @Override
    public ObjectShopSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.object_shop_selection, parent, false);
        return new ObjectShopSelectionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ObjectShopSelectionAdapter.MyViewHolder holder, int position) {
        ObjectDatabase objectDatabase = new ObjectDatabase(context);
        CurrencyDatabase currencyDatabase = new CurrencyDatabase(context);
        int resourceId = context.getResources().getIdentifier(displayObjects.get(position).getObjectImage(), "drawable", context.getPackageName());

        holder.objectImage.setImageResource(resourceId);
        holder.objectName.setText(displayObjects.get(position).getObjectName());
        holder.chipPrice.setText(displayObjects.get(position).getChipPrice() + "");
        holder.glazePrice.setText(displayObjects.get(position).getGlazePrice() + "");

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inventoryModel.getChipToken() >=
                        displayObjects.get(holder.getBindingAdapterPosition()).getChipPrice() &&
                        inventoryModel.getGlazeToken() >=
                                displayObjects.get(holder.getBindingAdapterPosition()).getGlazePrice()) {

                    objectDatabase.addObject(objectSource.getObjectByString(holder.objectName.getText().toString()));
                    refreshInventory.refreshInventory();

                    inventoryModel.setChipToken(inventoryModel.getChipToken() -
                            displayObjects.get(holder.getBindingAdapterPosition()).getChipPrice());

                    inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() -
                            displayObjects.get(holder.getBindingAdapterPosition()).getGlazePrice());

                    currencyDatabase.updateToken(inventoryModel);

                    chipToken.setText(inventoryModel.getChipToken() + "");
                    glazeToken.setText(inventoryModel.getGlazeToken() + "");

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayObjects.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView objectImage;
        TextView objectName;
        TextView chipPrice, glazePrice;
        Button buyButton;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            objectImage = itemView.findViewById(R.id.objectImage);
            objectName = itemView.findViewById(R.id.objectName);
            chipPrice = itemView.findViewById(R.id.chipPrice);
            glazePrice = itemView.findViewById(R.id.glazePrice);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
