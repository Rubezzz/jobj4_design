package ru.job4j.ood.lsp.store;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.lsp.model.Food;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class ShopTest {

    @Test
    void whenAdd1ThenSize1() {
        Shop store = new Shop();
        store.add(new Food("Food", LocalDateTime.now(), LocalDateTime.now(), 100, 0));
        assertThat(store.size()).isEqualTo(1);
    }

    @Test
    void whenAdd2AndRemove1ThenSize1() {
        Shop store = new Shop();
        Food food1 = new Food("Food", LocalDateTime.now(), LocalDateTime.now(), 100, 0);
        Food food2 = new Food("Food2", LocalDateTime.now(), LocalDateTime.now(), 200, 0);
        store.add(food1);
        store.add(food2);
        store.remove(food1);
        assertThat(store.size()).isEqualTo(1);
    }

}