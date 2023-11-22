package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class BigSpace implements ParkingSpace {

    private Car car;

    public BigSpace() {
    }

    public BigSpace(Car car) {
        this.car = car;
    }

    @Override
    public void add(Car car) {
        if (car != null) {
            this.car = car;
        }
    }

    @Override
    public String toString() {
        return "BigSpace{"
                + "car=" + car
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
        BigSpace bigSpace = (BigSpace) o;
        return Objects.equals(car, bigSpace.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }
}
