package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FoolTest {

    @Test
    void when3thenFizz() {
        int num = 3;
        String expected = "Fizz";
        assertThat(Fool.equalsNumber(num)).isEqualTo(expected);
    }

    @Test
    void when20thenBuzz() {
        int num = 20;
        String expected = "Buzz";
        assertThat(Fool.equalsNumber(num)).isEqualTo(expected);
    }

    @Test
    void when30thenFizzBuzz() {
        int num = 30;
        String expected = "FizzBuzz";
        assertThat(Fool.equalsNumber(num)).isEqualTo(expected);
    }

    @Test
    void when4then5() {
        int num = 4;
        String expected = "4";
        assertThat(Fool.equalsNumber(num)).isEqualTo(expected);
    }
}