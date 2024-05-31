package com.example.miwipet.models.eggs;

import com.example.miwipet.models.EggModel;

public class ArcticEgg extends EggModel {
    private final String[][] petImages = {
            {"arctic_hare", "harp_seal", "arctic_tern", "arctic_fox",
                    "beluga_whale", "kittiwake", "arctic_cod"},


            {"albatross", "penguin", "elk", "dall_sheep",
                    "seal", "glaucous_gull"},


            {"great_gray_owl", "snowy_owl", "walrus", "polar_bear",
                    "puffin", "snowy_leopard", "reindeer"},


            {"orca", "mountain_goat", "narwhal", "wild_yak",
                    "arctic_wolf", "greenland_shark"},


            {"ice_wolf", "spirit_reindeer", "milky_way_albatross"}
    };

    private final String[][] petNames = {
            {"Arctic Hare", "Harp Seal", "Arctic Tern", "Arctic Fox",
                    "Beluga Whale", "Kittiwake", "Arctic Cod"},

            {"Albatross", "Penguin", "Elk", "Dall Sheep",
                    "Seal", "Glaucous Gull"},

            {"Great Gray Owl", "Snowy Owl", "Walrus", "Polar Bear",
                    "Puffin", "Snowy Leopard", "Reindeer"},

            {"Orca", "Mountain Goat", "Narwhal", "Wild Yak",
                    "Arctic Wolf", "Greenland Shark"},

            {"Ice Wolf", "Spirit Reindeer", "Milky Way Albatross"}
    };

    public ArcticEgg() {
        super("Arctic Egg", "egg_arctic", 30, 0, 0, 30, 2, 55);
        super.selectRarity(petImages, petNames);
    }
}
