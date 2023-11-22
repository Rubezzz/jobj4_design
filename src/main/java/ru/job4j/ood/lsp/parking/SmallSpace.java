package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class SmallSpace implements ParkingSpace {

    private Car car;

    public SmallSpace() {
    }

    public SmallSpace(Car car) {
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
        return "SmallSpace{"
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
        SmallSpace that = (SmallSpace) o;
        return Objects.equals(car, that.car);
    }

    @Override
    public int hashCode() {
        return Objects.hash(car);
    }
}
