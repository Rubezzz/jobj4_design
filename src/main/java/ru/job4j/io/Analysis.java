package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        String start = "";
        String end = "";
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] split = line.trim().split(" ");
                if (start.isEmpty() && ("400".equals(split[0]) || "500".equals(split[0]))) {
                    start = split[1];
                }
                if (!start.isEmpty() && ("200".equals(split[0]) || "300".equals(split[0]))) {
                    end = split[1];
                }
                if (!start.isEmpty() && !end.isEmpty()) {
                    out.append(start).append(";").append(end).append(";");
                    out.newLine();
                    start = "";
                    end = "";
                }
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