package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 2);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube");
    }

    @Test
    void isThisUnknown() {
        Box box = new Box(14, 5);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Unknown object");
    }

    @Test
    void whenNumberOfVertices8Then8() {
        Box box = new Box(8, 5);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(8);
    }

    @Test
    void whenNumberOfVertices14ThenMinus1() {
        Box box = new Box(14, 2);
        int vertex = box.getNumberOfVertices();
        assertThat(vertex).isEqualTo(-1);
    }

    @Test
    void whenNumberOfVertices8ThenIsExist() {
        Box box = new Box(8, 1);
        boolean isExist = box.isExist();
        assertThat(isExist).isTrue();
    }

    @Test
    void whenNumberOfVertices10ThenIsNotExist() {
        Box box = new Box(10, 10);
        boolean isExist = box.isExist();
        assertThat(isExist).isFalse();
    }

    @Test
    void whenVertices8AndEdge2ThenArea24() {
        Box box = new Box(8, 2);
        double area = box.getArea();
        assertThat(area).isEqualTo(24, withPrecision(0.01d));
    }

    @Test
    void whenVertices4AndEdge3ThenArea15dot59() {
        Box box = new Box(4, 3);
        double area = box.getArea();
        assertThat(area).isEqualTo(15.59, withPrecision(0.01d));
    }
}