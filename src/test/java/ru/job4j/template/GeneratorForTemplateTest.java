package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

@Disabled
class GeneratorForTemplateTest {

    @Test
    void whenProduceIsOK() {
        Generator generator = new GeneratorForTemplate();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject ", "you");
        String expected = "I am a Petr, Who are you?";
        assertThat(generator.produce(template, args)).isEqualTo(expected);
    }

    @Test
    void whenTemplateIs2ArgsMapIs3ArgsThenException() {
        Generator generator = new GeneratorForTemplate();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject ", "you", "object", "we");
        assertThatThrownBy(() -> generator.produce(template, args)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateIs2ArgsMapIs1ArgsThenException() {
        Generator generator = new GeneratorForTemplate();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr");
        assertThatThrownBy(() -> generator.produce(template, args)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenKeysIsNotEqualsThenException() {
        Generator generator = new GeneratorForTemplate();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "surname ", "Surname");
        assertThatThrownBy(() -> generator.produce(template, args)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void whenTemplateInValidThenException() {
        Generator generator = new GeneratorForTemplate();
        String template = "I am a name, Who are subject?";
        Map<String, String> args = Map.of("name", "Petr", "surname ", "Surname");
        assertThatThrownBy(() -> generator.produce(template, args)).isInstanceOf(IllegalArgumentException.class);
    }
}