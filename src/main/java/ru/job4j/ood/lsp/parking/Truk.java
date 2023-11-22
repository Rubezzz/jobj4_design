package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Truk implements Car {

    private String model;
    private int size;

    public Truk(String model, int size) {
        this.model = model;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void drive() {
        System.out.printf("Грузовая машина %s едет", model);
    }

    @Override
    public String toString() {
        return "Truk{"
                + "model='" + model + '\''
                + ", size=" + size
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truk truk = (Truk) o;
        return size == truk.size && Objects.equals(model, truk.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size);
    }
}
