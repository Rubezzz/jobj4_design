package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("1", "2", "3", "4");
        assertThat(list).isNotNull()
                .hasSize(4)
                .filteredOn(e -> Integer.parseInt(e) > 2)
                .hasSize(2);
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("1", "2", "3", "4", "5");
        assertThat(set).isNotNull()
                .allMatch(e -> e.length() == 1)
                .last()
                .isEqualTo("5");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("1", "2", "22", "3", "444", "5", "6");
        assertThat(map).isNotNull()
                .containsEntry("22", 2)
                .containsKeys("5", "6")
                .containsValue(0);
    }
}