package ru.job4j.ood.lsp.store;

import ru.job4j.ood.lsp.model.Food;

import java.util.List;

public interface Store {

    public void add(Food food);

    public void remove(Food food);

    public List<Food> getAll();
}
