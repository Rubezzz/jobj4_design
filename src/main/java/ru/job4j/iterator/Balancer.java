package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {

    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int size = nodes.size();
        int index = 0;
        while (source.hasNext()) {
            nodes.get(index).add(source.next());
            index++;
            if (index >= size) {
                index = 0;
            }
        }
    }
}