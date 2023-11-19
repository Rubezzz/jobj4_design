package ru.job4j.ood.lsp.control;

import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Store;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {

    List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void resort() {
        List<Food> tempList = stores.stream().flatMap(s -> s.getAll().stream()).toList();
        stores.clear();
        tempList.forEach(this::add);
    }

    public void add(Food food) {
        int percent = expiration(food);
        if (percent < 25) {
            stores.get(0).add(food);
        }
        if (percent >= 25 && percent < 100) {
            if (percent > 75) {
                food.setDiscount(20);
            }
            stores.get(1).add(food);
        }
        if (percent >= 100) {
            stores.get(2).add(food);
        }
    }

    private int expiration(Food food) {
        LocalDateTime now = LocalDateTime.now();
        long allTime = ChronoUnit.MINUTES.between(food.getCreateDate(), food.getExpiryDate());
        long timeBeforeNow = ChronoUnit.MINUTES.between(food.getCreateDate(), now);
        return (int) (timeBeforeNow / (allTime / 100));
    }
}
