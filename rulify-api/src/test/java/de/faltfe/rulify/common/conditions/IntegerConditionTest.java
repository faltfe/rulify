package de.faltfe.rulify.common.conditions;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class IntegerConditionTest {

    @Nested
    class IsEven {
        @ParameterizedTest
        @ValueSource(ints = {-2, 0, 2, 4})
        void withEvenNumbers(int number) {
            assertTrue(IntegerCondition.isEven.test(number));
        }

        @ParameterizedTest
        @ValueSource(ints = {-1, 1, 3})
        void withOddNumbers(int number) {
            assertFalse(IntegerCondition.isEven.test(number));
        }
    }

    @Nested
    class IsOdd {
        @ParameterizedTest
        @ValueSource(ints = {-1, 1, 3})
        void withEvenNumbers(int number) {
            assertTrue(IntegerCondition.isOdd.test(number));
        }

        @ParameterizedTest
        @ValueSource(ints = {-2, 0, 2, 4})
        void withOddNumbers(int number) {
            assertFalse(IntegerCondition.isOdd.test(number));
        }
    }

}