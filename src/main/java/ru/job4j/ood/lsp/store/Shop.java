package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

public class Shop extends AbstractStore {

    @Override
    public void add(Food food) {
        super.add(food);
        System.out.printf("Продукт %s прибыл в магазин", food);
    }

    @Override
    public void remove(Food food) {
        super.remove(food);
        System.out.printf("Продукт %s продан покупателю", food);
    }
}
