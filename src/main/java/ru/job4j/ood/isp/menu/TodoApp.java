package ru.job4j.ood.isp.menu;

import java.util.Optional;
import java.util.Scanner;

public class TodoApp {

    public static final ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action");

    private static void showMenu() {
        System.out.println("1. Добавить элемент в корень меню");
        System.out.println("2. Добавить элемент к родительскому элементу");
        System.out.println("3. Вызвать действие, привязанное к пункту меню");
        System.out.println("4. Вывести меню в консоль");
        System.out.println("Любой другой символ - выход из программы");
        System.out.println("Введите номер пункта меню: ");
    }

    private static String askStr(String question, Scanner scanner) {
        System.out.print(question);
        return scanner.nextLine();
    }

    public static void run(Menu menu) {
        boolean run = true;
        var io = new Scanner(System.in);
        String answer;
        while (run) {
            showMenu();
            answer = io.nextLine();
            switch (answer) {
                case "1":
                    menu.add(null, askStr("Введите название элемента: ", io), DEFAULT_ACTION);
                    break;
                case "2":
                    answer = askStr("Введите родительского элемента: ", io);
                    menu.add(answer, askStr("Введите название элемента: ", io), DEFAULT_ACTION);
                    break;
                case "3":
                    Optional<Menu.MenuItemInfo> select = menu.select(askStr("Введите название элемента: ", io));
                    select.ifPresent(menuItemInfo -> menuItemInfo.getActionDelegate().delegate());
                    break;
                case "4":
                    new SimpleMenuPrinter().print(menu);
                    break;
                default:
                    run = false;
            }
        }
    }

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        run(menu);
    }
}
