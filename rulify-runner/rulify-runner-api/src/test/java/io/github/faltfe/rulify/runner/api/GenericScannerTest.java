package io.github.faltfe.rulify.runner.api;

import io.github.faltfe.rulify.api.Executable;
import io.github.faltfe.rulify.runner.api.annotations.Rule;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GenericScannerTest {

    @Rule(Executable.class)
    static class AnnotatedClass1 {}

    @Rule(Executable.class)
    static class AnnotatedClass2 {}

    private GenericScanner<Rule> scanner;
    private static final String PATH_TO_SCAN = "junit.test";

    @BeforeEach
    void init() {
        this.scanner = new GenericScannerImpl(PATH_TO_SCAN);
    }

    @Nested
    class SetFoundClasses {
        @Test
        void withAnnotatedClass() {
            assertDoesNotThrow(() -> scanner.setFoundClasses(Set.of(AnnotatedClass1.class, AnnotatedClass2.class)));
        }

        @Test
        void withNotAnnotatedClass() {
            Set<Class<?>> classes = Set.of(Object.class, Objects.class);
            assertThrows(
                    IllegalArgumentException.class,
                    () -> scanner.setFoundClasses(classes)
            );
        }
    }

    @Test
    void getAnnotationToScan() {
        assertEquals(Rule.class, this.scanner.getAnnotationToScan());
    }

    @Nested
    class GetFoundClasses {

        @Test
        void noFoundClassesSet() {
            assertEquals(Collections.emptySet(), scanner.getFoundClasses());
        }

        @Test
        void foundClassesSet() {
            Set<Class<?>> classes = Set.of(AnnotatedClass1.class, AnnotatedClass2.class);
            scanner.setFoundClasses(classes);
            assertThat(scanner.getFoundClasses()).containsExactlyInAnyOrderElementsOf(classes);
        }
    }
}
