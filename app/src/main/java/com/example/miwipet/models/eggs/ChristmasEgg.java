package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class ChristmasEgg extends EggModel {
    int[][] pets = {
            {R.drawable.golden_walrus, R.drawable.ski_lion, R.drawable.gingerbread_mouse,
                    R.drawable.choco_milk_albatross, R.drawable.diamond_tern, R.drawable.snowball},


            {R.drawable.winter_seal, R.drawable.toy_mouse, R.drawable.christmas_puffin,
                    R.drawable.elf_squirrel, R.drawable.toy_cat, R.drawable.christmas_seal},


            {R.drawable.conductor_penguin, R.drawable.ice_cream_bear, R.drawable.rudolf,
                    R.drawable.gingerbread_walrus, R.drawable.yeti, R.drawable.snowman},


            {R.drawable.pudding_pug, R.drawable.candy_horse, R.drawable.strawberry_golem,
                    R.drawable.cream_narwhal, R.drawable.crystal_tern},


            {R.drawable.joy_reindeer, R.drawable.snowflake_dragon, R.drawable.aurora_dragon}
    };

    String[][] petNames = {
            {"Golden Walrus", "Ski Lion", "Gingerbread Mouse", "Choco-Milk Albatross", "Diamond Tern",
                    "Snowball"},

            {"Winter Seal", "Toy Mouse", "Christmas Puffin", "Elf Squirrel", "Toy Cat", "Christmas Seal"},

            {"Conductor Penguin", "Ice Cream Bear", "Rudolf", "Gingerbread Walrus", "Yeti", "Snowman"},

            {"Pudding Pug", "Candy Horse", "Strawberry Golem", "Cream Narwhal", "Crystal Tern"},

            {"Joy Reindeer", "Snowflake Dragon", "Aurora Dragon"}
    };

    public ChristmasEgg() {
        super("Christmas Egg", R.drawable.egg_christmas, 0, 0, 0, 0, 2, 100);
        super.selectRarity(pets, petNames);
    }
}
