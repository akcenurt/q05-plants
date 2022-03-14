package com.engeto.rostliny;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {

    public static final String DELIMITER = "\t";
    List<Plant> plants = new ArrayList<>();

    public List<Plant> getAllPlants() {
        // Vytvořím kopii listu a tu poskytnu jako výsledek volání:
        return new ArrayList<>(plants);
    }

    public void addPlant (Plant plant) {
        plants.add(plant);
    }

    public void removePlant (int index) {
        plants.remove(index);
    }

    public void getPlantInfo (int index) {
        plants.get(index);
    }

    public void loadFromFile (String filename, String delimiter) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] parts = inputLine.split(delimiter);
                String name = parts[0];
                String notes = parts[1];
                int frequencyOfWatering = Integer.parseInt(parts[2]);
                LocalDate watering = LocalDate.parse(parts[3]);
                LocalDate planted = LocalDate.parse(parts[4]);

                Plant plant = new Plant (name, notes, frequencyOfWatering, watering, planted);
                plants.add(plant); }
            }
            catch (IOException | NumberFormatException | PlantException | DateTimeException e) {
               e.printStackTrace();
            }
        }



        public void saveToFile (String inputFilename, String delimiter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(inputFilename))) {
            for (Plant plant : plants) {
                String outputLine = plant.getName()+delimiter;
                outputLine += plant.getNotes()+delimiter;
                outputLine += plant.getFrequencyOfWatering()+delimiter;
                outputLine += plant.getWatering().toString()+delimiter;
                outputLine += plant.getPlanted().toString()+delimiter;
                writer.println(outputLine);

            } }
            catch (IOException e) {
                e.printStackTrace();
            }

        }

    public void exportToFile(String output) throws PlantException {
        int lineNumber = 0;
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(output)))){
            for (Plant plant : plants) {
                String plantInLine = plant.prepareOutputString(DELIMITER);
                writer.println(plantInLine);
                lineNumber++;
            }
        } catch (IOException e) {
            throw new PlantException("Chyba při zápisu: "+output+" řádek: "+lineNumber+": "+e.getLocalizedMessage());
        }
    }




}
