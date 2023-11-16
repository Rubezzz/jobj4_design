package ru.job4j.kiss.fool;

import java.util.Scanner;
import java.util.function.Predicate;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (startAt % 3 == 0) {
                System.out.println("Fizz");
            } else if (startAt % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(startAt);
            }
            startAt++;
            var answer = io.nextLine();
            if (startAt % 3 == 0 && startAt % 5 == 0) {
                if (equalsAnswer(e -> !"FizzBuzz".equals(e), answer)) {
                    startAt = 0;
                }
            } else if (startAt % 3 == 0) {
                if (equalsAnswer(e -> !"Fizz".equals(e), answer)) {
                    startAt = 0;
                }
            } else if (startAt % 5 == 0) {
                if (equalsAnswer(e -> !"Buzz".equals(e), answer)) {
                    startAt = 0;
                }
            } else {
                if (equalsAnswer(e -> !String.valueOf(e).equals(e), answer)) {
                    startAt = 0;
                }
            }
            startAt++;
        }
    }

    public static boolean equalsAnswer(Predicate<String> predicate, String answer) {
        if (predicate.test(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            return true;
        }
        return false;
    }
}