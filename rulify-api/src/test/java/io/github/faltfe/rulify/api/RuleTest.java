package io.github.faltfe.rulify.api;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RuleTest {

    @SuppressWarnings("unchecked")
    private final Rule<Object> rule = (Rule<Object>) spy(Rule.class);

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

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testExecuteWithSupplier() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(true);
        when(rule.condition()).thenReturn(condition);

        Action action = mock(Action.class);
        when(rule.action()).thenReturn(action);

        rule.execute(Object::new);

        verify(rule, times(1)).condition();
        verify(rule, times(1)).action();
    }
}
