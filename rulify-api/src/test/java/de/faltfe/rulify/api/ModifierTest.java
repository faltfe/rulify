package de.faltfe.rulify.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ModifierTest {

    @Test
    void tap() {
        Modifier<Object> modifier = o -> o;
        modifier.tap(Assertions::assertEquals);
    }

    @Test
    void tapWithManipulation() {
        String phrase = "tap test";
        Modifier<String> modifier = String::toUpperCase;
        modifier.tap((in, out) -> {
            assertEquals(in, phrase);
            assertEquals(out, phrase.toUpperCase());
        }).apply("tap test");
    }
}