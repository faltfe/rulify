package de.faltfe.rulify.common.effects;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringEffectTest {

    @Nested
    class Capitalize {

        @Test
        void withValue() {
            assertEquals("Abc abc", StringEffect.capitalize.apply("abc abc"));
        }
    }

    @Nested
    class RemoveWhitespaces {
        @Test
        void withValue() {
            assertEquals("abcabc", StringEffect.removeWhitespaces.apply("abc abc"));
        }
    }

}