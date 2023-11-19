package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Trash extends AbstractStore {

    @Override
    public void add(Food food) {
        super.add(food);
        System.out.printf("Продукт %s прибыл на склад мусора", food);
    }

    @Override
    public void remove(Food food) {
        super.remove(food);
        System.out.printf("Продукт %s выброшен", food);
    }
}
