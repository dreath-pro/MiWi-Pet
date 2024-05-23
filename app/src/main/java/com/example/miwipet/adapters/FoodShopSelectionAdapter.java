package com.example.miwipet.adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miwipet.R;
import com.example.miwipet.database.FoodDatabase;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.utils.EggSource;
import com.example.miwipet.utils.FoodSource;
import com.example.miwipet.utils.RefreshInventory;

import java.util.ArrayList;

public class FoodShopSelectionAdapter extends RecyclerView.Adapter<FoodShopSelectionAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<FoodModel> displayFoods;
    private InventoryModel inventoryModel;
    private TextView chipToken, glazeToken;

    private RefreshInventory refreshInventory;
    private FoodSource foodSource;

    public FoodShopSelectionAdapter(
            Context context,
            FoodSource foodSource,
            ArrayList<FoodModel> displayFoods,
            TextView chipToken,
            TextView glazeToken,
            InventoryModel inventoryModel) {
        this.context = context;
        this.foodSource = foodSource;
        this.displayFoods = displayFoods;
        this.chipToken = chipToken;
        this.glazeToken = glazeToken;
        this.inventoryModel = inventoryModel;

        refreshInventory = new RefreshInventory(context, inventoryModel);
    }

    @NonNull
    @Override
    public FoodShopSelectionAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_shop_selection, parent, false);
        return new FoodShopSelectionAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodShopSelectionAdapter.MyViewHolder holder, int position) {
        FoodDatabase foodDatabase = new FoodDatabase(context);
        int resourceId = context.getResources().getIdentifier(displayFoods.get(position).getFoodImage(), "drawable", context.getPackageName());

        holder.foodImage.setImageResource(resourceId);
        holder.foodName.setText(displayFoods.get(position).getFoodName());
        holder.chipPrice.setText(displayFoods.get(position).getChipPrice() + "");
        holder.glazePrice.setText(displayFoods.get(position).getGlazePrice() + "");

        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inventoryModel.getChipToken() >=
                        displayFoods.get(holder.getAdapterPosition()).getChipPrice() &&
                        inventoryModel.getGlazeToken() >=
                                displayFoods.get(holder.getAdapterPosition()).getGlazePrice()) {

                    foodDatabase.addFood(foodSource.getFoodByString(holder.foodName.getText().toString()));
                    refreshInventory.getFoodFromDatabase();

                    inventoryModel.setChipToken(inventoryModel.getChipToken() -
                            displayFoods.get(holder.getAdapterPosition()).getChipPrice());

                    inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() -
                            displayFoods.get(holder.getAdapterPosition()).getGlazePrice());

                    chipToken.setText(inventoryModel.getChipToken() + "");
                    glazeToken.setText(inventoryModel.getGlazeToken() + "");

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayFoods.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView foodImage;
        TextView foodName;
        TextView chipPrice, glazePrice;
        Button buyButton;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            foodImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            chipPrice = itemView.findViewById(R.id.chipPrice);
            glazePrice = itemView.findViewById(R.id.glazePrice);
            buyButton = itemView.findViewById(R.id.buyButton);
        }
    }
}
