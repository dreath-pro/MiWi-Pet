package com.example.miwipet.logics;

import java.util.Random;

public class TradeGeneration {
    Random random = new Random();

    public TradeGeneration()
    {

    }

    public String generateName() {
        String[] nameList = {"Lily X boy", "Amandiax160", "cutiepie_x069", "dreath_pro", "Jade the grinder",
                "Liam Naz", "Marzie262", "Nath", "Joyce269"};
        int selectedName = random.nextInt(nameList.length);

        return nameList[selectedName];
    }

    public String generateProfile() {
        String[] userProfile = {"profile_amy", "profile_sasa", "profile_gabrielle", "profile_andrea",
                "profile_luke", "profile_jacob", "profile_kimberly", "profile_ashley", "profile_amanda",
                "profile_eve", "profile_matthew"};
        int selectedProfile = random.nextInt(userProfile.length);

        return userProfile[selectedProfile];
    }

    public int generateTradeHistory() {
        int tradeHistory = random.nextInt(1000) + 1;
        return tradeHistory;
    }
}
