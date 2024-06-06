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
import com.example.miwipet.database.FoodDatabase;
import com.example.miwipet.database.ObjectDatabase;
import com.example.miwipet.database.TimeDatabase;
import com.example.miwipet.fragments.navigation.AboutFragment;
import com.example.miwipet.fragments.navigation.ChangelogFragment;
import com.example.miwipet.fragments.navigation.CollectionFragment;
import com.example.miwipet.fragments.navigation.TierFragment;
import com.example.miwipet.fragments.single.EggFragment;
import com.example.miwipet.fragments.navigation.FlexFragment;
import com.example.miwipet.fragments.navigation.NurseryFragment;
import com.example.miwipet.fragments.navigation.StoreFragment;
import com.example.miwipet.fragments.navigation.TradeFragment;
import com.example.miwipet.models.EggModel;
import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.InventoryModel;
import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.PetModel;
import com.example.miwipet.database.CurrencyDatabase;
import com.example.miwipet.models.TimeModel;
import com.example.miwipet.utils.InspectInventory;
import com.example.miwipet.database.PetDatabase;
import com.example.miwipet.utils.Rarity;
import com.example.miwipet.utils.RefreshInventory;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

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
//female student, cute cartoon, digital art, hd, profile picture
//simple flower in a pot, 3d render, full view, white background, simplistic cute design

//icons used link
//* cracked egg
//https://www.flaticon.com/free-icon/egg_812971?term=crack+egg&page=1&position=5&origin=search&related_id=812971

//*shopping bag
//https://www.flaticon.com/free-icon/shopping-bag_253298?term=bag&page=1&position=8&origin=search&related_id=253298

//*store
//https://www.flaticon.com/free-icon/store_2639570?term=store&page=1&position=7&origin=search&related_id=2639570

//*trade
//https://www.flaticon.com/free-icon/transaction_5681355?term=trade&page=1&position=18&origin=search&related_id=5681355

//*flex
//https://www.flaticon.com/free-icon/arm-muscle_3525876?term=flex&page=1&position=7&origin=search&related_id=3525876

//*nursery
//https://www.flaticon.com/free-icon/alphabet_3359814?term=nursery&page=1&position=10&origin=search&related_id=3359814

//*changelog
//https://www.flaticon.com/free-icon/exchange_1372840?term=change&page=1&position=8&origin=search&related_id=1372840

//*info
//https://www.flaticon.com/free-icon/information-button_1176?term=info&page=1&position=8&origin=search&related_id=1176

//*exit
//https://www.flaticon.com/free-icon/logout_660350?term=exit&page=1&position=2&origin=search&related_id=660350

//*tier
//https://www.flaticon.com/free-icon/pyramid_6403728?term=tier&page=1&position=6&origin=search&related_id=6403728

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    FrameLayout frameLayout;
    NavigationView navigationView;
    TextView chipTokenValue, glazeTokenValue;
    ImageView eggImage;
    TextView timeText;
    Button hatchButton;
    TextView errorText;

    private ArrayList<EggModel> incubated = new ArrayList<>();
    private InventoryModel inventoryModel = new InventoryModel();
    private TimeModel timeModel = new TimeModel();


    private RefreshInventory refreshInventory;
    private Rarity rarity = new Rarity();


    private PetDatabase petDatabase = new PetDatabase(MainActivity.this);
    private EggDatabase eggDatabase = new EggDatabase(MainActivity.this);
    private FoodDatabase foodDatabase = new FoodDatabase(MainActivity.this);
    private ObjectDatabase objectDatabase = new ObjectDatabase(MainActivity.this);

    private CurrencyDatabase currencyDatabase = new CurrencyDatabase(MainActivity.this);
    private TimeDatabase timeDatabase = new TimeDatabase(MainActivity.this);


    private Date currentTime;

    private String formattedDay;
    private String formattedMonth;
    private String formattedYear;

    private void initializeCurrentTime() {
        currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat currentDay = new SimpleDateFormat("dd", Locale.getDefault());
        SimpleDateFormat currentMonth = new SimpleDateFormat("MM", Locale.getDefault());
        SimpleDateFormat currentYear = new SimpleDateFormat("yyyy", Locale.getDefault());

        formattedDay = currentDay.format(currentTime);
        formattedMonth = currentMonth.format(currentTime);
        formattedYear = currentYear.format(currentTime);
    }

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
        errorText = findViewById(R.id.errorText);

        errorText.setVisibility(View.INVISIBLE);
    }

    private void generateTime() {
        if (!timeDatabase.doesDataExist()) {
            generateTimeModel(false);
            timeDatabase.generateTable(timeModel);
        } else {
            generateTimeModel(true);
            timeDatabase.updateTime(timeModel);
        }

        timeModel.setLoggedIn(false);
    }

    private void generateTimeModel(boolean doesDataExist) {
        if (doesDataExist) {
            timeModel = timeDatabase.getTimeRecord();

            timeModel.setCurrentTime(currentTime.toString());
            timeModel.setCurrentTimeLogin(currentTime.toString());
            timeModel.setCurrentDayLogin(formattedDay);
            timeModel.setCurrentMonthLogin(formattedMonth);
            timeModel.setCurrentYearLogin(formattedYear);

            if (timeModel.isNewDay()) {
                timeModel.setLoggedIn(true);
                timeModel.setLoginStreak(timeModel.getLoginStreak() + 1);

                if (timeModel.missedLogin()) {
                    timeModel.setLoginStreak(1);
                }
            }
        } else {
            timeModel.setCurrentTime(currentTime.toString());
            timeModel.setCurrentTimeLogin(currentTime.toString());
            timeModel.setCurrentDayLogin(formattedDay);
            timeModel.setCurrentMonthLogin(formattedMonth);
            timeModel.setCurrentYearLogin(formattedYear);

            timeModel.setLastTimeLogin(timeModel.getCurrentTimeLogin());
            timeModel.setLastDayLogin(timeModel.getCurrentDayLogin());
            timeModel.setLastMonthLogin(timeModel.getCurrentMonthLogin());
            timeModel.setLastYearLogin(timeModel.getCurrentYearLogin());

            timeModel.setLoggedIn(true);
            timeModel.setLoginStreak(1);
        }
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

    private void resetInventory() {
        petDatabase.clearPet();
        eggDatabase.clearEgg();
        foodDatabase.clearFood();
        objectDatabase.clearObject();

        for (PetModel petModel : inventoryModel.getPetLists()) {
            petDatabase.addPet(petModel);
        }

        for (EggModel eggModel : inventoryModel.getEggLists()) {
            eggDatabase.addEgg(eggModel);
        }

        for (FoodModel foodModel : inventoryModel.getFoodLists()) {
            foodDatabase.addFood(foodModel);
        }

        for (ObjectModel objectModel : inventoryModel.getObjectLists()) {
            objectDatabase.addObject(objectModel);
        }

        refreshInventory.getPetFromDatabase();
        refreshInventory.getEggFromDatabase();
        refreshInventory.getFoodFromDatabase();
        refreshInventory.getObjectFromDatabase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            refreshInventory = new RefreshInventory(MainActivity.this, inventoryModel);

            initializeComponents();
            initializeCurrentTime();
            generateTime();
            generateToken();
            refreshInventory.getPetFromDatabase();
            refreshInventory.getEggFromDatabase();
            refreshInventory.getFoodFromDatabase();
            refreshInventory.getObjectFromDatabase();

            InspectInventory inspectInventory = new InspectInventory(inventoryModel);
            inspectInventory.updatePet();
            inspectInventory.updateEggImage();
            inspectInventory.updatePetImage();
            inspectInventory.updateFoodImage();
            inspectInventory.updateObjectImage();

            resetInventory();

            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_close, R.string.drawer_open);
            drawerLayout.addDrawerListener(toggle);

            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if (item.getItemId() == R.id.home_nav) {
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } else if (item.getItemId() == R.id.collection_nav) {
                        replaceFragment(new CollectionFragment(inventoryModel, getApplicationContext()));
                    } else if (item.getItemId() == R.id.store_nav) {
                        replaceFragment(new StoreFragment(inventoryModel, chipTokenValue, glazeTokenValue, timeModel));
                    } else if (item.getItemId() == R.id.trade_nav) {
                        replaceFragment(new TradeFragment(inventoryModel));
                    } else if (item.getItemId() == R.id.flex_nav) {
                        replaceFragment(new FlexFragment());
                    } else if (item.getItemId() == R.id.nursery_nav) {
                        replaceFragment(new NurseryFragment());
                    } else if (item.getItemId() == R.id.tier_nav) {
                        replaceFragment(new TierFragment());
                    } else if (item.getItemId() == R.id.changelog_nav) {
                        replaceFragment(new ChangelogFragment());
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
                        int resourceId = getResources().getIdentifier(incubated.get(0).getPetImage(), "drawable", getPackageName());
                        eggImage.setImageResource(resourceId);

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

                        petModel.setType(0);
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
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_SHORT).show();
            errorText.setVisibility(View.VISIBLE);
            errorText.setText("Error: " + e);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        timeModel.setLastTimeLogin(timeModel.getCurrentTimeLogin());
        timeModel.setLastDayLogin(timeModel.getCurrentDayLogin());
        timeModel.setLastMonthLogin(timeModel.getCurrentMonthLogin());
        timeModel.setLastYearLogin(timeModel.getCurrentYearLogin());

        timeDatabase.updateTime(timeModel);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}