package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class Audi implements Car {

    private String model;
    private int size;

    public Audi(String model, int size) {
        this.model = model;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void drive() {
        System.out.printf("Легковая машина %s едет", model);
    }

    @Override
    public String toString() {
        return "Audi{"
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
        Audi audi = (Audi) o;
        return size == audi.size && Objects.equals(model, audi.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, size);
    }
}
