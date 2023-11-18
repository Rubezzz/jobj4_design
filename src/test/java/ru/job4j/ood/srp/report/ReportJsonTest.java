package ru.job4j.ood.srp.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import ru.job4j.ood.srp.formatter.DateTimeParser;
import ru.job4j.ood.srp.formatter.ReportDateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.store.MemStore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJsonTest {

    @Test
    void whenGenerateJson() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        Gson gson = new GsonBuilder().create();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Max", now, now, 150);
        store.add(worker);
        store.add(worker2);
        Report engine = new ReportJson(store, parser, gson);
        String formattedDate = parser.parse(now);
        StringBuilder expect = new StringBuilder()
                .append("[")
                .append("{")
                .append("\"name\":\"Ivan\",")
                .append("\"hired\":\"").append(formattedDate).append("\",")
                .append("\"fired\":\"").append(formattedDate).append("\",")
                .append("\"salary\":100.0")
                .append("},")
                .append("{")
                .append("\"name\":\"Max\",")
                .append("\"hired\":\"").append(formattedDate).append("\",")
                .append("\"fired\":\"").append(formattedDate).append("\",")
                .append("\"salary\":150.0")
                .append("}")
                .append("]");
        String cleanOutput = engine.generate(em -> true).replaceAll("\n", "").replaceAll("\r", "");
        assertThat(cleanOutput).isEqualTo(expect.toString());
    }
}