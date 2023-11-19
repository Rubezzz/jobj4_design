package ru.job4j.ood.lsp.control;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;
import ru.job4j.ood.lsp.store.Shop;
import ru.job4j.ood.lsp.store.Store;
import ru.job4j.ood.lsp.store.Trash;
import ru.job4j.ood.lsp.store.Warehouse;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    void whenAdd() {
        List<Store> stores = Arrays.asList(new Warehouse(), new Shop(), new Trash());
        ControlQuality control = new ControlQuality(stores);
        LocalDateTime now = LocalDateTime.now();
        Food food1 = new Food("Сыр", now.plusDays(1), now.minusDays(10), 300, 0);
        Food food2 = new Food("Консерва", now.plusDays(100), now.minusDays(1), 120, 0);
        Food food3 = new Food("Просрочка", now.minusDays(5), now.minusDays(35), 200, 0);
        Food food4 = new Food("Просрочка 2", now.minusDays(15), now.minusDays(70), 400, 0);
        control.add(food1);
        control.add(food2);
        control.add(food3);
        control.add(food4);
        Store warehouse = control.stores.get(0);
        Store shop = control.stores.get(1);
        Store trash = control.stores.get(2);
    }
}