package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(true, "Honda", 2006,
                new String[]{"118", "White"}, new Owner("Ivan", "Ivanov"));
        final Gson gson = new GsonBuilder().create();
        String strGson = gson.toJson(car);
        System.out.println(strGson);
        final Car newCar = gson.fromJson(strGson, Car.class);
        System.out.println(newCar);
    }
}