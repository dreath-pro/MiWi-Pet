package com.example.miwipet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miwipet.R;
import com.example.miwipet.fragments.AboutFragment;
import com.example.miwipet.fragments.CollectionFragment;
import com.example.miwipet.fragments.HistoryFragment;
import com.example.miwipet.fragments.InboxFragment;
import com.example.miwipet.fragments.StoreFragment;
import com.example.miwipet.fragments.TradeFragment;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.NormalEgg;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

//egg hatch, logo, simple
//husky, 3d render, full body view, white background, simplistic kiddy design, cute
//frost egg, 3d render, white background, simple design
//ocean background, digital art, cartoon, unfocus, blur

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TextView chipTokenValue, glazeTokenValue;
    Integer chipToken = 0, glazeToken = 0;
    ImageView eggImage;
    TextView timeText;
    Button hatchButton;

    private void initializeComponents()
    {
        drawerLayout = findViewById(R.id.drawer_layout);
        materialToolbar = findViewById(R.id.materialToolbar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        chipTokenValue = findViewById(R.id.chipTokenValue);
        glazeTokenValue = findViewById(R.id.glazeTokenValue);
        eggImage = findViewById(R.id.eggImage);
        timeText = findViewById(R.id.timeText);
        hatchButton = findViewById(R.id.hatchButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
            drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home_nav)
                {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }else if(item.getItemId() == R.id.collection_nav)
                {
                    replaceFragment(new CollectionFragment());
                }else if(item.getItemId() == R.id.store_nav)
                {
                    replaceFragment(new StoreFragment());
                }else if(item.getItemId() == R.id.trade_nav)
                {
                    replaceFragment(new TradeFragment());
                }else if(item.getItemId() == R.id.history_nav)
                {
                    replaceFragment(new HistoryFragment());
                }else if(item.getItemId() == R.id.inbox_nav)
                {
                    replaceFragment(new InboxFragment());
                }else if(item.getItemId() == R.id.about_nav)
                {
                    replaceFragment(new AboutFragment());
                }else if(item.getItemId() == R.id.exit_nav)
                {
                    System.exit(0);
                }

                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });

        hatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EggModel normalEgg = new NormalEgg();
                eggImage.setImageResource(normalEgg.getPetBirth());
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}