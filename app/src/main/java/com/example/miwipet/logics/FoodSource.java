package com.example.miwipet.logics;

import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.foods.Apple;
import com.example.miwipet.models.foods.Banana;
import com.example.miwipet.models.foods.BirthdayCake;
import com.example.miwipet.models.foods.Biscuit;
import com.example.miwipet.models.foods.Broccoli;
import com.example.miwipet.models.foods.Burger;
import com.example.miwipet.models.foods.Cabbage;
import com.example.miwipet.models.foods.Cheese;
import com.example.miwipet.models.foods.ChocolateBar;
import com.example.miwipet.models.foods.CoconutJuice;
import com.example.miwipet.models.foods.Corn;
import com.example.miwipet.models.foods.FriedFish;
import com.example.miwipet.models.foods.HotDog;
import com.example.miwipet.models.foods.IceCream;
import com.example.miwipet.models.foods.Juice;
import com.example.miwipet.models.foods.Lollipop;
import com.example.miwipet.models.foods.Mango;
import com.example.miwipet.models.foods.Meat;
import com.example.miwipet.models.foods.Milk;
import com.example.miwipet.models.foods.MushroomSoup;
import com.example.miwipet.models.foods.Noodle;
import com.example.miwipet.models.foods.Pancake;
import com.example.miwipet.models.foods.Pineapple;
import com.example.miwipet.models.foods.Pizza;
import com.example.miwipet.models.foods.Salad;
import com.example.miwipet.models.foods.Smoothie;
import com.example.miwipet.models.foods.Starfruit;
import com.example.miwipet.models.foods.Steak;
import com.example.miwipet.models.foods.Sushi;

import java.util.ArrayList;
import java.util.Random;

public class FoodSource {
    private ArrayList<FoodModel> foodLists = new ArrayList<>();

    public FoodSource() {

    }

    private void generateFoods(String type) {
        foodLists.clear();

        switch (type) {
            case "All":
                foodLists.add(new Burger());
                foodLists.add(new IceCream());
                foodLists.add(new Noodle());
                foodLists.add(new Pizza());
                foodLists.add(new Sushi());
                foodLists.add(new Apple());
                foodLists.add(new Banana());
                foodLists.add(new BirthdayCake());
                foodLists.add(new Biscuit());
                foodLists.add(new Broccoli());
                foodLists.add(new Cabbage());
                foodLists.add(new Cheese());
                foodLists.add(new ChocolateBar());
                foodLists.add(new CoconutJuice());
                foodLists.add(new Corn());
                foodLists.add(new FriedFish());
                foodLists.add(new HotDog());
                foodLists.add(new Juice());
                foodLists.add(new Lollipop());
                foodLists.add(new Mango());
                foodLists.add(new Meat());
                foodLists.add(new Milk());
                foodLists.add(new MushroomSoup());
                foodLists.add(new Pancake());
                foodLists.add(new Pineapple());
                foodLists.add(new Salad());
                foodLists.add(new Smoothie());
                foodLists.add(new Starfruit());
                foodLists.add(new Steak());
                break;
            case "Store":
                foodLists.add(new Burger());
                foodLists.add(new IceCream());
                foodLists.add(new Noodle());
                foodLists.add(new Pizza());
                foodLists.add(new Sushi());
                foodLists.add(new Apple());
                foodLists.add(new Banana());
                foodLists.add(new Biscuit());
                foodLists.add(new Broccoli());
                foodLists.add(new Cabbage());
                foodLists.add(new Cheese());
                foodLists.add(new ChocolateBar());
                foodLists.add(new CoconutJuice());
                foodLists.add(new Corn());
                foodLists.add(new FriedFish());
                foodLists.add(new HotDog());
                foodLists.add(new Juice());
                foodLists.add(new Lollipop());
                foodLists.add(new Mango());
                foodLists.add(new Meat());
                foodLists.add(new Milk());
                foodLists.add(new MushroomSoup());
                foodLists.add(new Pancake());
                foodLists.add(new Pineapple());
                foodLists.add(new Smoothie());
                foodLists.add(new Starfruit());
                foodLists.add(new Steak());
                break;
            case "Birthday":
                foodLists.add(new BirthdayCake());
                foodLists.add(new Salad());
                break;
        }
    }

    public ArrayList<FoodModel> getAllFoods() {
        generateFoods("All");
        return foodLists;
    }

    public ArrayList<FoodModel> getStoreFoods() {
        generateFoods("Store");

        ArrayList<FoodModel> selectedFoodsList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <= foodLists.size() - 1; i++) {
            int chances = random.nextInt(100) + 1;

            if (chances <= foodLists.get(i).getFoodPercentage()) {
                selectedFoodsList.add(foodLists.get(i));
            }
        }

        foodLists.clear();
        foodLists.addAll(selectedFoodsList);

        return foodLists;
    }

    public int getCount() {
        return foodLists.size();
    }

    public FoodModel pickOverallFood(int index) {
        generateFoods("All");
        return foodLists.get(index);
    }

    public ArrayList<FoodModel> getFoodsByString(ArrayList<String> foodNames) {
        ArrayList<FoodModel> returnedFoodList = new ArrayList<>();

        generateFoods("All");

        for (String foodName : foodNames) {
            for (FoodModel foodList : foodLists) {
                if (foodName.equals(foodList.getFoodName())) {
                    returnedFoodList.add(foodList);
                }
            }
        }

        return returnedFoodList;
    }

    public FoodModel getFoodByString(String foodName) {
        FoodModel returnedFood = null;

        generateFoods("All");

        for (FoodModel foodList : foodLists) {
            if (foodName.equals(foodList.getFoodName())) {
                returnedFood = foodList;
            }
        }

        return returnedFood;
    }
}
