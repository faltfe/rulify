package de.faltfe.rulify.runner;

import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class RuleRunnerTest {

    @Test
    void testRunnerExecution() {
        RuleRunner runner = mock(RuleRunner.class, withSettings().useConstructor(""));
        doCallRealMethod().when(runner).run();
        runner.run();
//        verify(runner).executeRules(any());
    }

}
