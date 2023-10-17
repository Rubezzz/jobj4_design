package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {

    private final boolean isWorking;
    private final String model;
    private final int year;
    private final String[] parameters;
    private final Owner owner;

    public Car(boolean isWorking, String model, int year, String[] parameters, Owner owner) {
        this.isWorking = isWorking;
        this.model = model;
        this.year = year;
        this.parameters = parameters;
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Car{"
                + "isWorking=" + isWorking
                + ", model='" + model + '\''
                + ", year=" + year
                + ", parameters=" + Arrays.toString(parameters)
                + ", owner=" + owner
                + '}';
    }
}
