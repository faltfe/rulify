package de.faltfe.rulify.internal;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Rule;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BaseRuleTest {

    private final Rule<?> rule = mock(Rule.class, Mockito.CALLS_REAL_METHODS);

    @Nested
    class InCase {

        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withCondition() {
            Condition mockedCondition = mock(Condition.class);
            rule.inCase(mockedCondition);
            verify(mockedCondition).test(any());
        }

        @Test
        void withNull() {
            assertThrows(NullPointerException.class, () -> rule.inCase(null));
        }
    }

    @Nested
    class ThenRun {
        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withAction() {
            Action mockedAction = mock(Action.class);
            rule.thenRun(mockedAction);
            verify(mockedAction).accept(any());
        }

        @Test
        void withNull() {
            assertThrows(NullPointerException.class, () -> rule.thenRun(null));
        }
    }

}
