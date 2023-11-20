package ru.job4j.ood.lsp.parking;

import java.util.List;

public interface Parking {

    void add(Car car);

    void remove(Car car);

    List<ParkingSpace> listAllSpace();
}
