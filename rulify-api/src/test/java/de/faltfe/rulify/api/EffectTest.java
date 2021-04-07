package de.faltfe.rulify.api;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EffectTest {

    private final Effect<?> effect = mock(Effect.class, Mockito.CALLS_REAL_METHODS);

    @Nested
    class Manipulate {
        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withSideEffect() {
            Modifier mockedModifier = mock(Modifier.class);
            when(mockedModifier.apply(any())).thenReturn(true);
            assertNotNull(effect.manipulate(mockedModifier));
            verify(mockedModifier).apply(any());
        }

        @Test
        void withNull() {
            assertThrows(NullPointerException.class, () -> effect.manipulate(null));
        }
    }

    @Nested
    class Execute {
        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withSuccess() {
            Condition condition = mock(Condition.class);
            when(condition.test(any())).thenReturn(true);
            when(effect.condition()).thenReturn(condition);

            Modifier modifier = mock(Modifier.class);
            when(modifier.apply(any())).thenReturn(true);
            when(effect.effect()).thenReturn(modifier);

            Action action = mock(Action.class);
            when(effect.action()).thenReturn(action);

            effect.execute();
            verify(effect, times(1)).action();
            verify(effect, times(1)).manipulate(modifier);
        }

        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withFailedCondition() {
            Condition condition = mock(Condition.class);
            when(condition.test(any())).thenReturn(false);
            when(effect.condition()).thenReturn(condition);

            effect.execute();
            verify(effect, never()).action();
            verify(effect, never()).manipulate(any(Modifier.class));
        }

        @Test
        void withNullCondition() {
            when(effect.condition()).thenReturn(null);
            assertThrows(NullPointerException.class, effect::execute);
        }

        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withNullEffect() {
            Condition condition = mock(Condition.class);
            when(condition.test(any())).thenReturn(true);
            when(effect.effect()).thenReturn(null);
            assertThrows(NullPointerException.class, effect::execute);
        }

        @SuppressWarnings(value = {"rawtypes", "unchecked"})
        @Test
        void withNullAction() {
            Condition condition = mock(Condition.class);
            when(condition.test(any())).thenReturn(true);
            Modifier modifier = mock(Modifier.class);
            when(modifier.apply(any())).thenReturn(true);
            when(effect.effect()).thenReturn(modifier);
            when(effect.action()).thenReturn(null);
            assertThrows(NullPointerException.class, effect::execute);
        }
    }


}
