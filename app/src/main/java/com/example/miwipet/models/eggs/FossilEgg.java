package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class FossilEgg extends EggModel {
    private final int[][] references = {
            {R.drawable.brachiosaurus, R.drawable.t_rex, R.drawable.smilodon,
                    R.drawable.mammoth, R.drawable.paraceratherium},

            {R.drawable.titanoboa, R.drawable.triceratops, R.drawable.spinosaurus,
                    R.drawable.parasaurolophus, R.drawable.megalodon},

            {R.drawable.ankylosaurus, R.drawable.stegosaurus, R.drawable.plesiosaurus,
                    R.drawable.amargasaurus, R.drawable.sarcosuchus},

            {R.drawable.pteranodon, R.drawable.dodo, R.drawable.dunkleosteus,
                    R.drawable.phorusrhacids, R.drawable.glyptodon},

            {R.drawable.poly_rex, R.drawable.skeleton_t_rex, R.drawable.leviathan}
    };

    private final String[][] petImages = {
            {"brachiosaurus", "t_rex", "smilodon",
                    "mammoth", "paraceratherium"},


            {"titanoboa", "triceratops", "spinosaurus",
                    "parasaurolophus", "megalodon"},


            {"ankylosaurus", "stegosaurus", "plesiosaurus",
                    "amargasaurus", "sarcosuchus"},


            {"pteranodon", "dodo", "dunkleosteus",
                    "phorusrhacids", "glyptodon"},


            {"poly_rex", "skeleton_t_rex", "leviathan"}
    };

    private final String[][] petNames = {
            {"Brachiosaurus", "T-Rex", "Smilodon", "Mammoth", "Paraceratherium"},

            {"Titanoboa", "Triceratops", "Spinosaurus", "Parasaurolophus", "Megalodon"},

            {"Ankylosaurus", "Stegosaurus", "Plesiosaurus", "Amargasaurus", "Sarcosuchus"},

            {"Pteranodon", "Dodo", "Dunkleosteus", "Phorusrhacids", "Glyptodon"},

            {"Poly Rex", "Skeleton T-Rex", "Leviathan"}
    };

    public FossilEgg() {
        super("Fossil Egg", "egg_fossil", 0, 0, 0, 30, 1, 100);
        super.selectRarity(petImages, petNames);
    }
}
