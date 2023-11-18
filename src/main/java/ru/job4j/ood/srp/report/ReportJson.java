package ru.job4j.ood.srp.report;

import com.google.gson.*;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportJson implements Report {

    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private Gson gson;

    public ReportJson(Store store, DateTimeParser<Calendar> dateTimeParser, Gson gson) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.gson = gson;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        JsonArray jsonArray = new JsonArray();
        for (Employee e : store.findBy(filter)) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("name", e.getName());
            jsonObject.addProperty("hired", dateTimeParser.parse(e.getHired()));
            jsonObject.addProperty("fired", dateTimeParser.parse(e.getFired()));
            jsonObject.addProperty("salary", e.getSalary());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toString();
    }
}
