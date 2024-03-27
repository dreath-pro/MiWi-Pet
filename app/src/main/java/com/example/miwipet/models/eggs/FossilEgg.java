package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class FossilEgg extends EggModel {
    int[][] pets = {
            {R.drawable.brachiosaurus, R.drawable.t_rex, R.drawable.smilodon, R.drawable.mammoth},


            {R.drawable.titanoboa, R.drawable.triceratops, R.drawable.spinosaurus},


            {R.drawable.ankylosaurus, R.drawable.stegosaurus, R.drawable.megalodon},


            {R.drawable.deinosuchus, R.drawable.pteranodon, R.drawable.mosasaurus,
                    R.drawable.dunkleosteus},


            {R.drawable.polymesh_rex, R.drawable.skeleton_t_rex, R.drawable.blazing_pteranodon}
    };

    String[][] petNames = {
            {"Brachiosaurus", "T-Rex", "Smilodon", "Mammoth"},

            {"Titanoboa", "Triceratops", "Spinosaurus"},

            {"Ankylosaurus", "Stegosaurus", "Megalodon"},

            {"Deinosuchus", "Pteranodon", "Mosasaurus", "Dunkleosteus"},

            {"Polymesh Rex", "Skeleton T-Rex", "Blazing Pteranodon"}
    };

    public FossilEgg() {
        super("Fossil Egg", R.drawable.egg_fossil, 0, 30, 1);
        super.selectRarity(pets, petNames);
    }
}
