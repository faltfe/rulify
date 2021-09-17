package de.faltfe.rulify.common.conditions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoolConditionTest {

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void alwaysTrue(boolean expression) {
        assertTrue(BoolCondition.alwaysTrue().test(expression));
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void alwaysFalse(boolean expression) {
        assertFalse(BoolCondition.alwaysFalse().test(expression));
    }
}