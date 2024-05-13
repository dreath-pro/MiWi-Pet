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
import com.example.miwipet.database.EggDatabase;
import com.example.miwipet.database.TimeDatabase;
import com.example.miwipet.fragments.navigation.AboutFragment;
import com.example.miwipet.fragments.navigation.CollectionFragment;
import com.example.miwipet.fragments.single.EggFragment;
import com.example.miwipet.fragments.navigation.HistoryFragment;
import com.example.miwipet.fragments.navigation.InboxFragment;
import com.example.miwipet.fragments.navigation.StoreFragment;
import com.example.miwipet.fragments.navigation.TradeFragment;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.database.CurrencyDatabase;
import com.example.miwipet.models.TimeModel;
import com.example.miwipet.utils.InspectInventory;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.utils.Rarity;
import com.example.miwipet.utils.RefreshInventory;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

//egg hatch, logo, simple
//philippine eagle a monkey eating eagle with beautiful feathers, 3d render, full view, white background, simplistic kiddy design, cute
//frost egg, 3d render, white background, simple design
//ocean background, digital art, cartoon, unfocus, blur
//simple noodles, 3d render, full view, white background, simplistic kiddy design

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
    private TimeModel timeModel = new TimeModel();

    private RefreshInventory refreshInventory;
    private Rarity rarity = new Rarity();

    private PetDatabase petDatabase = new PetDatabase(MainActivity.this);
    private CurrencyDatabase currencyDatabase = new CurrencyDatabase(MainActivity.this);
    private TimeDatabase timeDatabase = new TimeDatabase(MainActivity.this);

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

    private void generateTime()
    {
        if(!timeDatabase.doesDataExist())
        {
            generateTimeModel(false);
            timeDatabase.generateTable(timeModel);
        }else
        {
            generateTimeModel(true);
            timeDatabase.updateTime(timeModel);
        }

        timeModel.setLoggedIn(false);
    }

    private void generateTimeModel(boolean doesDataExist)
    {
        TimeModel currentTimeModel = new TimeModel();

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat currentDay = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat currentMonth = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat currentYear = new SimpleDateFormat("yyyy", Locale.getDefault());

        String formattedDay = currentDay.format(currentTime);
        String formattedMonth = currentMonth.format(currentTime);
        String formattedYear = currentYear.format(currentTime);

        currentTimeModel.setCurrentTime(currentTime.toString());
        currentTimeModel.setLastTimeLogin(currentTime.toString());
        currentTimeModel.setLastDayLogin(formattedDay);
        currentTimeModel.setLastMonthLogin(formattedMonth);
        currentTimeModel.setLastYearLogin(formattedYear);

        timeModel = timeDatabase.getTimeRecord();

        if(doesDataExist)
        {
            if(timeModel.isNewDay(currentTimeModel))
            {
                timeModel.setLoggedIn(true);
                timeModel.setLoginStreak(timeModel.getLoginStreak() + 1);

                if(timeModel.missedLogin(currentTimeModel))
                {
                    timeModel.setLoginStreak(1);
                }
            }
        }else
        {
            timeModel.setLoggedIn(true);
            timeModel.setLoginStreak(1);
        }

        timeModel.setCurrentTime(currentTimeModel.getCurrentTime());
        timeModel.setLastTimeLogin(currentTimeModel.getCurrentTime());
        timeModel.setLastDayLogin(currentTimeModel.getLastDayLogin());
        timeModel.setLastMonthLogin(currentTimeModel.getLastMonthLogin());
        timeModel.setLastYearLogin(currentTimeModel.getLastYearLogin());
    }

    private void generateToken() {
        if (!currencyDatabase.doesDataExist()) {
            currencyDatabase.generateTokens();

            inventoryModel.setChipToken(10);
            currencyDatabase.updateToken(inventoryModel);
        }
        updateToken();
    }

    private void updateToken() {
        inventoryModel.setChipToken(currencyDatabase.getChipToken());
        inventoryModel.setGlazeToken(currencyDatabase.getGlazeToken());

        chipTokenValue.setText("" + inventoryModel.getChipToken());
        glazeTokenValue.setText("" + inventoryModel.getGlazeToken());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshInventory = new RefreshInventory(MainActivity.this, inventoryModel);

        initializeComponents();
        generateTime();
        generateToken();
        refreshInventory.getPetFromDatabase();
        refreshInventory.getEggFromDatabase();

        InspectInventory inspectInventory = new InspectInventory(inventoryModel);
        inspectInventory.updatePet();
        inspectInventory.updateEggPetImage();
        inspectInventory.updatePetImage();

        petDatabase.clearPet();
        for (PetModel petModel : inventoryModel.getPetLists()) {
            petDatabase.addPet(petModel);
        }
        inventoryModel.clearPetLists();
        refreshInventory.getPetFromDatabase();

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
                            rarity.getRarity(0),
                            rarity.getRarity(1),
                            rarity.getRarity(2),
                            rarity.getRarity(3),
                            rarity.getRarity(4)};

                    if (petModel.getRarity().equals(rarities[0])) {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 10);
                    } else if (petModel.getRarity().equals(rarities[1])) {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 20);
                    } else if (petModel.getRarity().equals(rarities[2])) {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 30);
                    } else if (petModel.getRarity().equals(rarities[3])) {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 40);
                        inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() + 10);
                    } else if (petModel.getRarity().equals(rarities[4])) {
                        inventoryModel.setChipToken(inventoryModel.getChipToken() + 50);
                        inventoryModel.setGlazeToken(inventoryModel.getGlazeToken() + 20);
                    }

                    currencyDatabase.updateToken(inventoryModel);
                    updateToken();

                    petDatabase.addPet(petModel);
                    refreshInventory.getPetFromDatabase();

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
                    replaceFragment(new EggFragment(inventoryModel, incubated, eggImage));
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
}