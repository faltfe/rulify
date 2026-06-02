package io.github.faltfe.rulify.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ThrowerTest {

    @SuppressWarnings("unchecked")
    private final Thrower<Object> rule = (Thrower<Object>) spy(Thrower.class);

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    void executeThrowsWhenConditionMatches() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(true);
        when(rule.condition()).thenReturn(condition);
        when(rule.exception(any())).thenReturn(new IllegalStateException("matched"));

        assertThrows(IllegalStateException.class, rule::execute);
        verify(rule, times(1)).condition();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    void executeDoesNotThrowWhenConditionFails() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(false);
        when(rule.condition()).thenReturn(condition);

        assertDoesNotThrow(() -> rule.execute());
        verify(rule, times(1)).condition();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    void executeWithSupplierThrowsWhenConditionMatches() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(true);
        when(rule.condition()).thenReturn(condition);
        when(rule.exception(any())).thenReturn(new IllegalArgumentException("supplier match"));

        assertThrows(IllegalArgumentException.class, () -> rule.execute(Object::new));
        verify(rule, times(1)).condition();
    }
}
