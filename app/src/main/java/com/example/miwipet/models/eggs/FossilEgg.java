package com.example.miwipet.models.eggs;

import com.example.miwipet.R;
import com.example.miwipet.models.EggModel;

public class FossilEgg extends EggModel {
    private final String[][] petImages = {
            {"brachiosaurus", "t_rex", "smilodon",
                    "mammoth", "paraceratherium"},


            {"titanoboa", "triceratops", "spinosaurus",
                    "parasaurolophus", "megalodon"},


            {"ankylosaurus", "stegosaurus", "plesiosaurus",
                    "amargasaurus", "sarcosuchus"},


            {"pteranodon", "dodo", "dunkleosteus",
                    "phorusrhacids", "glyptodon"},


            {"polymesh_rex", "skeleton_t_rex", "leviathan"}
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
