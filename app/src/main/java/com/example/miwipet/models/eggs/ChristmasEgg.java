package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ChristmasEgg extends EggModel {
    private final String[][] petImages = {
            {"golden_walrus", "ski_lion", "gingerbread_mouse",
                    "choco_milk_albatross", "diamond_tern", "snowball"},


            {"winter_seal", "toy_mouse", "christmas_puffin",
                    "elf_squirrel", "toy_cat", "christmas_seal"},


            {"conductor_penguin", "ice_cream_bear", "rudolf",
                    "gingerbread_walrus", "yeti", "snowman"},


            {"pudding_pug", "candy_horse", "strawberry_golem",
                    "cream_narwhal", "crystal_tern", "queen_snowflake"},


            {"joy_reindeer", "snowflake_dragon", "aurora_dragon"}
    };

    private final String[][] petNames = {
            {"Golden Walrus", "Ski Lion", "Gingerbread Mouse", "Choco-Milk Albatross", "Diamond Tern",
                    "Snowball"},

            {"Winter Seal", "Toy Mouse", "Christmas Puffin", "Elf Squirrel", "Toy Cat", "Christmas Seal"},

            {"Conductor Penguin", "Ice Cream Bear", "Rudolf", "Gingerbread Walrus", "Yeti", "Snowman"},

            {"Pudding Pug", "Candy Horse", "Strawberry Golem", "Cream Narwhal", "Crystal Tern",
                    "Queen Snowflake"},

            {"Joy Reindeer", "Snowflake Dragon", "Aurora Dragon"}
    };

    public ChristmasEgg() {
        super("Christmas Egg", "egg_christmas", 0, 0, 0, 0, 2, 100);
        super.selectRarity(petImages, petNames);
    }
}
