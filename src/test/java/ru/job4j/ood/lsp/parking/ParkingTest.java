package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Disabled
class ParkingTest {

    @Test
    void add() {
        Parking parking = new MoskowParking(3, 1);
        Car smallCar = new Audi("q7", 1);
        Car bigCar = new Truk("man", 3);
        parking.add(smallCar);
        parking.add(bigCar);
        List<ParkingSpace> expect = Arrays.asList(
                new SmallSpace(smallCar),
                new SmallSpace(),
                new SmallSpace(),
                new BigSpace(bigCar)
        );
        List<ParkingSpace> result = parking.listAllSpace();
        assertThat(result).isEqualTo(expect);
    }
}