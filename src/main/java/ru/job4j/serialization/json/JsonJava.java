package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonJava {

    public static void main(String[] args) {
        final Car car = new Car(true, "Honda", 2006,
                new String[]{"118", "White"}, new Owner("Ivan", "Ivanov"));
        String jsonStr = new JSONObject(car).toString();
        System.out.println(jsonStr);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("working", car.isWorking());
        jsonObject.put("model", car.getModel());
        jsonObject.put("year", car.getYear());
        jsonObject.put("parameters", new JSONArray(car.getParameters()));
        jsonObject.put("owner", new JSONObject(car.getOwner()));
        System.out.println(jsonObject);
    }
}
