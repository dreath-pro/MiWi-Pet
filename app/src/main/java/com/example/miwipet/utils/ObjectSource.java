package com.example.miwipet.utils;

import com.example.miwipet.models.ObjectModel;
import com.example.miwipet.models.objects.Backpack;
import com.example.miwipet.models.objects.Cap;
import com.example.miwipet.models.objects.FlowerPot;

import java.util.ArrayList;
import java.util.Random;

public class ObjectSource {
    private ArrayList<ObjectModel> objectLists = new ArrayList<>();

    public ObjectSource() {

    }

    private void generateObjects(String type) {
        objectLists.clear();

        switch (type) {
            case "All":
                objectLists.add(new Cap());
                objectLists.add(new FlowerPot());
                objectLists.add(new Backpack());
                break;
            case "Store":
                objectLists.add(new Cap());
                objectLists.add(new FlowerPot());
                objectLists.add(new Backpack());
                break;
        }
    }

    public ArrayList<ObjectModel> getAllObjects() {
        generateObjects("All");
        return objectLists;
    }

    public ArrayList<ObjectModel> getStoreObjects() {
        generateObjects("Store");

        ArrayList<ObjectModel> selectedObjectList = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i <= objectLists.size() - 1; i++) {
            int chances = random.nextInt(100) + 1;

            if (chances <= objectLists.get(i).getObjectChance()) {
                selectedObjectList.add(objectLists.get(i));
            }
        }

        objectLists.clear();
        objectLists.addAll(selectedObjectList);

        return objectLists;
    }

    public int getCount() {
        return objectLists.size();
    }

    public ObjectModel pickOverallObject(int index) {
        generateObjects("All");
        return objectLists.get(index);
    }

    public ArrayList<ObjectModel> getObjectByString(ArrayList<String> objectNames) {
        ArrayList<ObjectModel> returnedObjectList = new ArrayList<>();

        generateObjects("All");

        for (String objectName : objectNames) {
            for (ObjectModel objectList : objectLists) {
                if (objectName.equals(objectList.getObjectName())) {
                    returnedObjectList.add(objectList);
                }
            }
        }

        return returnedObjectList;
    }

    public ObjectModel getObjectByString(String objectName) {
        ObjectModel returnedObject = null;

        generateObjects("All");

        for (ObjectModel objectList : objectLists) {
            if (objectName.equals(objectList.getObjectName())) {
                returnedObject = objectList;
            }
        }

        return returnedObject;
    }
}
