package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {

    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            String[] split = item.getNumber().split("\\.");
            System.out.printf("%s%s%s%n",
                    "\t".repeat(split.length - 1),
                    item.getNumber(),
                    item.getName());
        }
    }
}
