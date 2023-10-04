package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {

    public void unavailable(String source, String target) {
        String start = "";
        String end = "";
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] split = line.trim().split(" ");
                if (start.isEmpty() && "400".equals(split[0]) || "500".equals(split[0])) {
                    start = split[1];
                }
                if (!start.isEmpty() && "200".equals(split[0]) || "300".equals(split[0])) {
                    end = split[1];
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    rsl.add(start + ";" + end + ";");
                    start = "";
                    end = "";
                }
            }
            for (String str: rsl) {
                out.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}