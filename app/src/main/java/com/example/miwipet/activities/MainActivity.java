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
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.models.eggs.ChristmasEgg;
import com.example.miwipet.models.eggs.ForestEgg;
import com.example.miwipet.models.eggs.FossilEgg;
import com.example.miwipet.models.eggs.NormalEgg;
import com.example.miwipet.models.eggs.OceanEgg;
import com.example.miwipet.utils.CurrencyDatabase;
import com.example.miwipet.utils.PetDatabase;
import com.example.miwipet.utils.RarityNames;
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

    private ArrayList<EggModel> incubated = new ArrayList<>();
    private InventoryModel inventoryModel = new InventoryModel();

    private PetDatabase petDatabase = new PetDatabase(MainActivity.this);
    private CurrencyDatabase currencyDatabase = new CurrencyDatabase(MainActivity.this);
    private RarityNames rarityNames = new RarityNames();

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

    private void generateToken()
    {
        if(!currencyDatabase.doesDataExist())
        {
            currencyDatabase.generateTokens();
        }
        updateToken();
    }

    private void updateToken()
    {
        inventoryModel.setChipToken(currencyDatabase.getChipToken());
        inventoryModel.setGlazeToken(currencyDatabase.getGlazeToken());

        chipTokenValue.setText("" + inventoryModel.getChipToken());
        glazeTokenValue.setText("" + inventoryModel.getGlazeToken());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
        generateToken();
        getPetFromDatabase();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
        drawerLayout.addDrawerListener(toggle);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_nav) {
                    getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else if (item.getItemId() == R.id.collection_nav) {
                    replaceFragment(new CollectionFragment(inventoryModel.getPetLists(), getApplicationContext()));
                } else if (item.getItemId() == R.id.store_nav) {
                    replaceFragment(new StoreFragment(inventoryModel, chipTokenValue, glazeTokenValue));
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
                if (!incubated.isEmpty()) {
                    PetModel petModel;

                    eggImage.setImageResource(incubated.get(0).getPetImage());
                    petModel = new PetModel(incubated.get(0).getPetName(), incubated.get(0).getPetImage(),
                            incubated.get(0).getAge(), incubated.get(0).getType(), incubated.get(0).getRarityText());

                    String[] rarities = new String[]{
                            rarityNames.getRarity(0),
                            rarityNames.getRarity(1),
                            rarityNames.getRarity(2),
                            rarityNames.getRarity(3),
                            rarityNames.getRarity(4)};

                    if(petModel.getRarity().equals(rarities[0]))
                    {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 10);
                    }else if(petModel.getRarity().equals(rarities[1]))
                    {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 20);
                    }else if(petModel.getRarity().equals(rarities[2]))
                    {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 30);
                    }else if(petModel.getRarity().equals(rarities[3]))
                    {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 40);
                        inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() + 10);
                    }else if(petModel.getRarity().equals(rarities[4]))
                    {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 50);
                        inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() + 20);
                    }

                    currencyDatabase.updateToken(inventoryModel);
                    updateToken();

                    petDatabase.addPet(petModel);
                    getPetFromDatabase();

                    incubated.clear();
                } else {
                    Toast.makeText(MainActivity.this, "Please tap the egg to select first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        eggImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (incubated.isEmpty()) {
                    replaceFragment(new EggFragment(inventoryModel.getEggLists(), incubated, eggImage));
                } else {
                    Toast.makeText(MainActivity.this, "Please hatch the egg first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Hahaha ni stop na jimhardcore", Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getPetFromDatabase()
    {
        ArrayList<PetModel> petModels;
        petModels = petDatabase.getPetList();

        inventoryModel.clearPetLists();
        for(PetModel pet : petModels)
        {
            inventoryModel.addPetLists(pet);
        }
    }
}