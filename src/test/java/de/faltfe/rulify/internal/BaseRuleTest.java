package de.faltfe.rulify.internal;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BaseRuleTest {

    private final Rule<?> rule = mock(Rule.class, Mockito.CALLS_REAL_METHODS);

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testInCase() {
        Condition mockedCondition = mock(Condition.class);
        rule.inCase(mockedCondition);
        verify(mockedCondition).test(any());
    }

    @Test
    void testInCaseWithNull() {
        assertThrows(IllegalArgumentException.class, () -> rule.inCase(null));
    }

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testThenRun() {
        Action mockedAction = mock(Action.class);
        rule.thenRun(mockedAction);
        verify(mockedAction).accept(any());
    }

    @Test
    void testThenRunWithNull() {
        assertThrows(IllegalArgumentException.class, () -> rule.thenRun(null));
    }

}
