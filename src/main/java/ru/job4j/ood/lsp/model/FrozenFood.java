package ru.job4j.ood.lsp.model;

import java.time.LocalDateTime;

public class FrozenFood extends Food {

    private final int minTemperature;

    public FrozenFood(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount, int minTemp) {
        super(name, expiryDate, createDate, price, discount);
        this.minTemperature = minTemp;
    }
}
