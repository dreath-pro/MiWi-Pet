package com.example.miwipet.utils;

import com.example.miwipet.models.FoodModel;
import com.example.miwipet.models.foods.Burger;
import com.example.miwipet.models.foods.IceCream;
import com.example.miwipet.models.foods.Noodle;
import com.example.miwipet.models.foods.Pizza;
import com.example.miwipet.models.foods.Sushi;

import java.util.ArrayList;
import java.util.Random;

public class FoodSource {
    private ArrayList<FoodModel> foodLists = new ArrayList<>();

    public FoodSource()
    {

    }

    private void generateFoods(String type)
    {
        foodLists.clear();

        switch (type)
        {
            case "All":
                foodLists.add(new Burger());
                foodLists.add(new IceCream());
                foodLists.add(new Noodle());
                foodLists.add(new Pizza());
                foodLists.add(new Sushi());
                break;
            case "Store":
                foodLists.add(new Burger());
                foodLists.add(new IceCream());
                foodLists.add(new Noodle());
                foodLists.add(new Pizza());
                foodLists.add(new Sushi());
                break;
        }
    }

    public ArrayList<FoodModel> getAllFoods()
    {
        generateFoods("All");
        return foodLists;
    }

    public ArrayList<FoodModel> getStoreFoods()
    {
        generateFoods("Store");

        ArrayList<FoodModel> selectedFoodsList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i <= foodLists.size() - 1; i++)
        {
            int chances = random.nextInt(100) + 1;

            if(chances <= foodLists.get(i).getFoodPercentage())
            {
                selectedFoodsList.add(foodLists.get(i));
            }
        }

        foodLists.clear();
        foodLists.addAll(selectedFoodsList);

        return foodLists;
    }

    public int getCount()
    {
        return foodLists.size();
    }

    public FoodModel pickOverallFood(int index)
    {
        generateFoods("All");
        return foodLists.get(index);
    }

    public ArrayList<FoodModel> getFoodsByString(ArrayList<String> foodNames)
    {
        ArrayList<FoodModel> returnedFoodList = new ArrayList<>();

        generateFoods("All");

        for(String foodName : foodNames)
        {
            for(FoodModel foodList : foodLists)
            {
                if(foodName.equals(foodList.getFoodName()))
                {
                    returnedFoodList.add(foodList);
                }
            }
        }

        return returnedFoodList;
    }

    public FoodModel getFoodByString(String foodName)
    {
        FoodModel returnedFood = null;

        generateFoods("All");

        for(FoodModel foodList : foodLists)
        {
            if(foodName.equals(foodList.getFoodName()))
            {
                returnedFood = foodList;
            }
        }

        return returnedFood;
    }
}
