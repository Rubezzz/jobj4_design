package ru.job4j.kiss.fool;

import java.util.Scanner;

public class Fool {

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        var io = new Scanner(System.in);
        while (startAt < 100) {
            System.out.println(equalsNumber(startAt));
            startAt++;
            var answer = io.nextLine();
            if (!equalsNumber(startAt).equals(answer)) {
                System.out.println("Ошибка. Начинай снова.");
                startAt = 0;
            }
            startAt++;
        }
    }

    public static String equalsNumber(int num) {
        String rsl;
        if (num % 3 == 0 && num % 5 == 0) {
            rsl = "FizzBuzz";
        } else if (num % 3 == 0) {
            rsl =  "Fizz";
        } else if (num % 5 == 0) {
            rsl =  "Buzz";
        } else {
            rsl = Integer.toString(num);
        }
        return rsl;
    }
}