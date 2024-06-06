package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ArcticEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.arctic_hare, R.drawable.harp_seal, R.drawable.arctic_tern, R.drawable.arctic_fox,
                    R.drawable.beluga_whale, R.drawable.kittiwake, R.drawable.arctic_cod},

            {R.drawable.albatross, R.drawable.penguin, R.drawable.elk, R.drawable.dall_sheep,
                    R.drawable.seal, R.drawable.glaucous_gull},

            {R.drawable.great_gray_owl, R.drawable.snowy_owl, R.drawable.walrus, R.drawable.polar_bear,
                    R.drawable.puffin, R.drawable.snowy_leopard, R.drawable.reindeer},

            {R.drawable.orca, R.drawable.mountain_goat, R.drawable.narwhal, R.drawable.wild_yak,
                    R.drawable.arctic_wolf, R.drawable.greenland_shark},

            {R.drawable.ice_wolf, R.drawable.spirit_reindeer, R.drawable.milky_way_albatross}
    };

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
