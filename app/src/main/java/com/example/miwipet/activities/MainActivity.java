package com.example.miwipet.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
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
import com.example.miwipet.fragments.EggFragment;
import com.example.miwipet.fragments.HistoryFragment;
import com.example.miwipet.fragments.InboxFragment;
import com.example.miwipet.fragments.StoreFragment;
import com.example.miwipet.fragments.TradeFragment;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.FossilEgg;
import com.example.miwipet.models.eggs.NormalEgg;
import com.example.miwipet.models.eggs.OceanEgg;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Random;

//egg hatch, logo, simple
//parasaurolophus, 3d render, full body view, white background, simplistic kiddy design, cute
//frost egg, 3d render, white background, simple design
//ocean background, digital art, cartoon, unfocus, blur

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TextView chipTokenValue, glazeTokenValue;
    ImageView eggImage;
    TextView timeText;
    Button hatchButton;

    private ArrayList<EggModel> eggInventory = new ArrayList<>();
    private ArrayList<EggModel> incubated = new ArrayList<>();

    private void initializeComponents() {
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

    private void generateEggs() {
        for (int i = 0; i <= 80; i++) {
            Random random = new Random();
            int selectedEgg = random.nextInt(4);

            switch (selectedEgg)
            {
                case 0:
                    eggInventory.add(new NormalEgg());
                    break;
                case 1:
                    eggInventory.add(new ForestEgg());
                    break;
                case 2:
                    eggInventory.add(new OceanEgg());
                    break;
                case 3:
                    eggInventory.add(new FossilEgg());
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        generateEggs();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_nav) {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (item.getItemId() == R.id.collection_nav) {
                    replaceFragment(new CollectionFragment());
                } else if (item.getItemId() == R.id.store_nav) {
                    replaceFragment(new StoreFragment());
                } else if (item.getItemId() == R.id.trade_nav) {
                    replaceFragment(new TradeFragment());
                } else if (item.getItemId() == R.id.history_nav) {
                    replaceFragment(new HistoryFragment());
                } else if (item.getItemId() == R.id.inbox_nav) {
                    replaceFragment(new InboxFragment());
                } else if (item.getItemId() == R.id.about_nav) {
                    replaceFragment(new AboutFragment());
                } else if (item.getItemId() == R.id.exit_nav) {
                    System.exit(0);
                }

                drawerLayout.closeDrawer(Gravity.LEFT);
                return false;
            }
        });

        hatchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!incubated.isEmpty())
                {
                    eggImage.setImageResource(incubated.get(0).getPetImage());
                    incubated.clear();
                }else
                {
                    Toast.makeText(MainActivity.this, "Please tap the egg to select first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eggImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(incubated.isEmpty())
                {
                    replaceFragment(new EggFragment(eggInventory, incubated, eggImage));
                }else
                {
                    Toast.makeText(MainActivity.this, "Please hatch the egg first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}