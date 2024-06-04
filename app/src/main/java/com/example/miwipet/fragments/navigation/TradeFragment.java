package com.example.miwipet.fragments.navigation;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miwipet.R;
import com.example.miwipet.adapters.MyViewPagerAdapter;
import com.example.miwipet.models.InventoryModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TradeFragment extends Fragment {
    TabLayout tradeTab;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;

    private InventoryModel yourInventory;

    public TradeFragment(InventoryModel yourInventory)
    {
        this.yourInventory = yourInventory;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trade, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tradeTab = view.findViewById(R.id.tradeTab);
        viewPager2 = view.findViewById(R.id.viewPager2);
        myViewPagerAdapter = new MyViewPagerAdapter(this, yourInventory);
        viewPager2.setAdapter(myViewPagerAdapter);

        new TabLayoutMediator(tradeTab, viewPager2,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Make Offer");
                            break;
                        case 1:
                            tab.setText("Find Offer");
                            break;
                    }
                }).attach();
    }
}