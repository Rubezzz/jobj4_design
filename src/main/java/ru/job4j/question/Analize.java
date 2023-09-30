package ru.job4j.question;

import java.util.*;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Info res = new Info(0, 0, 0);
        Map<Integer, String> previousMap = previous.stream().collect(Collectors.toMap(User::getId, User::getName));
        Map<Integer, String> currentMap = current.stream().collect(Collectors.toMap(User::getId, User::getName));
        for (Integer key : currentMap.keySet()) {
            if (!previousMap.containsKey(key)) {
                res.setAdded(res.getAdded() + 1);
            } else if (!currentMap.get(key).equals(previousMap.get(key))) {
                res.setChanged(res.getChanged() + 1);
            }
        }
        for (Integer key : previousMap.keySet()) {
            if (!currentMap.containsKey(key)) {
                res.setDeleted(res.getDeleted() + 1);
            }
        }
        return res;
    }
}