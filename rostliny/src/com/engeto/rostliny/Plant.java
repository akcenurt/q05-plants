package com.engeto.rostliny;

import java.time.LocalDate;

public class Plant {

    String name;
    String notes;
    LocalDate planted;
    LocalDate watering;
    int frequencyOfWatering;

    public Plant(String name) throws PlantException {
        this(name, 7, LocalDate.now());
    }


    public Plant(String name, int frequencyOfWatering, LocalDate planted) throws PlantException{
        this(name, " ",frequencyOfWatering, LocalDate.now(), planted);
    }

    public Plant(String name, String notes, int frequencyOfWatering, LocalDate watering, LocalDate planted) throws PlantException{
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.setWatering(watering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }

    public String prepareOutputString(String delimiter) {
        return getName() + delimiter
                + getNotes() + delimiter
                + getFrequencyOfWatering() + delimiter
                + getWatering() + delimiter
                + getPlanted();
    }



    public String getWateringInfo() {
        return "Název květiny: "+name+", datum poslední zálivky: "+watering+", datum doporučené další zálivky: "+LocalDate.now().plusDays(frequencyOfWatering)+".";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

//    Obdobně ošetřete zadávání data poslední zálivky — nesmí být starší než datum zasazení rostliny.

    public void setWatering(LocalDate watering) throws PlantException{
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum poslední zálivky nesmí být před datem zasazení rostliny. Zadal jsi: "+watering);
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }


    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering <= 0) {
            throw new PlantException("Je třeba zadat kladné celé číslo, zadal jsi: "+frequencyOfWatering);
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }
}
