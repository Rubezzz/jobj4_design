package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class MoskowParking implements Parking {

    private List<SmallSpace> smallSpaces;
    private int countSmall;
    private List<BigSpace> bigSpaces;
    private int countBig;
    private int sizeSmallSpace;
    private int sizeBigSpace;

    private void init(int counSmall, int counBig) {
        smallSpaces = new ArrayList<>(counSmall);
        for (int i = 1; i <= counSmall; i++) {
            smallSpaces.add(new SmallSpace());
        }
        bigSpaces = new ArrayList<>(counBig);
        for (int i = 1; i <= counBig; i++) {
            bigSpaces.add(new BigSpace());
        }
    }

    public MoskowParking(int counSmallSpace, int counBigSpace) {
        this.sizeBigSpace = counBigSpace;
        this.sizeSmallSpace = counSmallSpace;
        init(counSmallSpace, counBigSpace);
    }

    @Override
    public void add(Car car) {
        if (car.size() == 1) {
            if (countSmall < sizeSmallSpace) {
                smallSpaces.get(countSmall++).add(car);
            }
        } else {
            if (countBig < sizeBigSpace) {
                bigSpaces.get(countBig++).add(car);
            }
        }
    }

    @Override
    public void remove(Car car) {

    }

    @Override
    public List<ParkingSpace> listAllSpace() {
        List<ParkingSpace> spaces = new ArrayList<>(sizeBigSpace + sizeSmallSpace);
        spaces.addAll(smallSpaces);
        spaces.addAll(bigSpaces);
        return spaces;
    }
}
