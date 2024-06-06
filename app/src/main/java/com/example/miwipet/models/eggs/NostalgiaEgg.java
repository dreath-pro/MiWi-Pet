package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class NostalgiaEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.nostalgia_butterfly, R.drawable.nostalgia_cat, R.drawable.nostalgia_rhino},

            {R.drawable.nostalgia_elephant, R.drawable.nostalgia_flamingo, R.drawable.nostalgia_owl,
                    R.drawable.nostalgia_parrot, R.drawable.nostalgia_snake, R.drawable.nostalgia_tiger},

            {R.drawable.nostalgia_bear, R.drawable.nostalgia_deer, R.drawable.nostalgia_lion,
                    R.drawable.nostalgia_shark, R.drawable.nostalgia_smilodon, R.drawable.nostalgia_t_rex,
                    R.drawable.nostalgia_triceratops, R.drawable.nostalgia_whale_shark, R.drawable.nostalgia_crocodile},

            {R.drawable.nostalgia_hydra, R.drawable.nostalgia_minotaur, R.drawable.nostalgia_phoenix,
                    R.drawable.nostalgia_rock_golem, R.drawable.nostalgia_sphinx},

            {R.drawable.ninja_monkey, R.drawable.lavender_dragon}
    };

    private final String[][] petImages = {
            {"nostalgia_butterfly", "nostalgia_cat", "nostalgia_rhino"},


            {"nostalgia_elephant", "nostalgia_flamingo", "nostalgia_owl",
                    "nostalgia_parrot", "nostalgia_snake", "nostalgia_tiger"},


            {"nostalgia_bear", "nostalgia_deer", "nostalgia_lion",
                    "nostalgia_shark", "nostalgia_smilodon", "nostalgia_t_rex",
                    "nostalgia_triceratops", "nostalgia_whale_shark",
                    "nostalgia_crocodile"},


            {"nostalgia_hydra", "nostalgia_minotaur", "nostalgia_phoenix",
                    "nostalgia_rock_golem", "nostalgia_sphinx"},


            {"ninja_monkey", "lavender_dragon"}
    };

    private final String[][] petNames = {
            {"Nostalgia Butterfly", "Nostalgia Cat", "Nostalgia Rhino"},

            {"Nostalgia Elephant", "Nostalgia Flamingo", "Nostalgia Owl", "Nostalgia Parrot",
                    "Nostalgia Snake", "Nostalgia Tiger"},

            {"Nostalgia Bear", "Nostalgia Deer", "Nostalgia Lion", "Nostalgia Shark",
                    "Nostalgia Smilodon", "Nostalgia T-Rex", "Nostalgia Triceratops",
                    "Nostalgia Whale Shark", "Nostalgia Crocodile"},

            {"Nostalgia Hydra", "Nostalgia Minotaur", "Nostalgia Phoenix", "Nostalgia Rock Golem",
                    "Nostalgia Sphinx"},

            {"Ninja Monkey", "Lavender Dragon"}
    };

    public NostalgiaEgg() {
        super("Nostalgia Egg", "egg_nostalgia", 30, 10, 0, 30, 5, 20);
        super.selectRarity(petImages, petNames);
    }
}
