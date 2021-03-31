package de.faltfe.rulify.api;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EffectTest {

    private final Effect<?> effect = mock(Effect.class, Mockito.CALLS_REAL_METHODS);

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testManipulate() {
        SideEffect mockedSideEffect = mock(SideEffect.class);
        when(mockedSideEffect.apply(any())).thenReturn(true);
        assertNotNull(effect.manipulate(mockedSideEffect));
        verify(mockedSideEffect).apply(any());
    }

    @Test
    void testManipulateWithNull() {
        assertThrows(NullPointerException.class, () -> effect.manipulate(null));
    }

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testExecute() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(true);
        when(effect.condition()).thenReturn(condition);

        SideEffect sideEffect = mock(SideEffect.class);
        when(sideEffect.apply(any())).thenReturn(true);
        when(effect.effect()).thenReturn(sideEffect);

        Action action = mock(Action.class);
        when(effect.action()).thenReturn(action);

        effect.execute();
        verify(effect, times(1)).action();
        verify(effect, times(1)).manipulate(sideEffect);
    }

    @SuppressWarnings(value = {"rawtypes", "unchecked"})
    @Test
    void testExecuteFail() {
        Condition condition = mock(Condition.class);
        when(condition.test(any())).thenReturn(false);
        when(effect.condition()).thenReturn(condition);

        effect.execute();
        verify(effect, never()).action();
        verify(effect, never()).manipulate(any(SideEffect.class));
    }

}
