package de.faltfe.rulify.common.effects;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathEffectTest {

    @Nested
    class Square {
        @ParameterizedTest
        @ValueSource(doubles = {0, 1, 5, 10})
        void withValues(double base) {
            assertEquals(Math.pow(base, 2), MathEffect.square().apply(base));
        }
    }

    @Nested
    class Cube {
        @ParameterizedTest
        @ValueSource(doubles = {0, 1, 5, 10})
        void withValues(double base) {
            assertEquals(Math.pow(base, 3), MathEffect.cube().apply(base));
        }
    }

    @Nested
    class Power {
        @ParameterizedTest
        @CsvSource({"0, 1", "1, 0"})
        void withValues(double base, double power) {
            assertEquals(Math.pow(base, power), MathEffect.power(power).apply(base));
        }
    }

    @Nested
    class Sqrt {
        @ParameterizedTest
        @ValueSource(doubles = {-1, 0, 1, 9})
        void withValues(double base) {
            assertEquals(Math.sqrt(base), MathEffect.sqrt().apply(base));
        }
    }

    @Nested
    class Root {
        @ParameterizedTest
        @CsvSource({"2, 9", "3, 27"})
        void withValues(double exponent, double root) {
            assertEquals(Math.pow(root, 1.0/exponent), MathEffect.root(exponent).apply(root));
        }
    }
}