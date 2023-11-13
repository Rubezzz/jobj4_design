package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.util.Scanner;

public class Emulator {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите директорию с кешируемыми файлами: ");
        AbstractCache<String, String> cache = new DirFileCache(scanner.nextLine());
        boolean run = true;
        while (run) {
            System.out.println("Введите название файла, содержимое которого хотите вывести. Для выхода введите exit ");
            String nextLine = scanner.nextLine();
            if ("exit".equals(nextLine)) {
                run = false;
            } else {
                System.out.println(cache.get(nextLine));
            }
        }
    }
}
