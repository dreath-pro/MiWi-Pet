package com.example.miwipet.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.miwipet.fragments.tab.FindOfferFragment;
import com.example.miwipet.fragments.tab.MakeOfferFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MakeOfferFragment();
            case 1:
                return new FindOfferFragment();
            default:
                return new MakeOfferFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
