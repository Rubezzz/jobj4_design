package ru.job4j.ood.lsp.model;

import java.time.LocalDateTime;

public class Food {
    private String name;
    private LocalDateTime expiryDate;
    private LocalDateTime createDate;
    private int price;
    private int discount;

    public Food(String name, LocalDateTime expiryDate, LocalDateTime createDate, int price, int discount) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public int getPrice() {
        return price - (price / 100 * discount);
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
