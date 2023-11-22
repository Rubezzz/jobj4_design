package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;

class SimpleMenuPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @BeforeEach
    public void setUpStream() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStream() {
        System.setOut(System.out);
    }

    @Test
    void whenPrint() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        StringBuilder expected = new StringBuilder()
                .append("1.Сходить в магазин").append(System.lineSeparator())
                .append("\t1.1.Купить продукты").append(System.lineSeparator())
                .append("\t\t1.1.1.Купить хлеб").append(System.lineSeparator())
                .append("2.Покормить собаку").append(System.lineSeparator());
        new SimpleMenuPrinter().print(menu);
        assertThat(outContent.toString()).isEqualTo(expected.toString());
    }
}