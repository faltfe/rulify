package de.faltfe.rulify.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class RuleTest {

    private final Rule<?> rule = mock(Rule.class, Mockito.CALLS_REAL_METHODS);

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testExecute() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(true);
        when(rule.condition()).thenReturn(condition);

        Action action = mock(Action.class);
        when(rule.action()).thenReturn(action);

        rule.execute();
        verify(rule, times(1)).condition();
        verify(rule, times(1)).action();
    }

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testExecuteFail() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(false);
        when(rule.condition()).thenReturn(condition);

        rule.execute();
        verify(rule, times(1)).condition();
        verify(rule, never()).action();
    }

}
