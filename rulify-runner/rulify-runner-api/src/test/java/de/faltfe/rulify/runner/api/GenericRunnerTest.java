package de.faltfe.rulify.runner.api;

import de.faltfe.rulify.runner.api.annotations.Rule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GenericRunnerTest {

    @Mock
    private GenericScanner<Rule> scanner;

    static class GenericRunnerMockImpl extends GenericRunner<Rule> {

        protected GenericRunnerMockImpl(GenericScanner<Rule> scanner) {
            super(scanner);
        }

        @Override
        public void run() {}
    }

    @Test
    void start() {
        GenericRunner<Rule> runner = new GenericRunnerMockImpl(scanner);
        runner.start((classes -> classes.forEach(System.out::println)));
        verify(scanner).scan();
    }

    @Test
    void startWithNullConsumer() {
        GenericRunner<Rule> runner = new GenericRunnerMockImpl(scanner);
        assertThrows(NullPointerException.class, () -> runner.start(null));
    }
}
