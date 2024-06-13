package com.example.miwipet.adapters;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.miwipet.fragments.tab.FindOfferFragment;
import com.example.miwipet.fragments.tab.MakeOfferFragment;
import com.example.miwipet.models.InventoryModel;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    private InventoryModel yourInventory;

    public MyViewPagerAdapter(@NonNull Fragment fragment, InventoryModel yourInventory) {
        super(fragment);
        this.yourInventory = yourInventory;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MakeOfferFragment(yourInventory);
            case 1:
                return new FindOfferFragment(yourInventory);
            default:
                return new MakeOfferFragment(yourInventory);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
