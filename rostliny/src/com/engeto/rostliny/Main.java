package com.engeto.rostliny;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static final String INPUT_FILENAME = "kvetiny.txt";
    public static final String DELIMITER = "\t";
    public static final String OUTPUT_FILENAME = "vystup.txt";

    public static void main(String[] args) {


        PlantList plantList = new PlantList();
        plantList.loadFromFile(INPUT_FILENAME, DELIMITER);
        for (Plant plant : plantList.getAllPlants()) { // plantList nestačí, je třeba zavolat metodu, která kopíruje list, abychom ho neposyktovali dál ke změně
            System.out.println(plant.getWateringInfo());
        }


        try {
            plantList.addPlant(new Plant ("Mandragora", "zakryj si uši", 50, LocalDate.now(), LocalDate.now()));
            plantList.addPlant(new Plant ("Žaberník", "rychle do vody", 2, LocalDate.now(), LocalDate.now()));
            plantList.removePlant(0);
        } catch (PlantException e) {
            e.printStackTrace();
        }

        plantList.saveToFile(INPUT_FILENAME, DELIMITER);

        try {
            plantList.exportToFile(OUTPUT_FILENAME);
        } catch (PlantException e) {
            e.printStackTrace();
        }






    }
}
