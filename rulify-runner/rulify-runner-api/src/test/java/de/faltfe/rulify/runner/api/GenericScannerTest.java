package de.faltfe.rulify.runner.api;

import de.faltfe.rulify.api.Executable;
import de.faltfe.rulify.runner.api.annotations.Rule;
import lombok.NonNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GenericScannerTest {

    static class GenericScannerMockImpl extends GenericScanner<Rule> {

        /**
         * Create a new instance of the scanner.
         *
         * @param packageName is the string representation of the package name where the scan should start. The
         *         package name can be used in the {@link #scan()} implementation. A {@link NullPointerException} is
         *         thrown when {@code null} is given as an argument.
         */
        public GenericScannerMockImpl(@NonNull String packageName) {
            super(packageName);
        }

        @Override
        public Set<Class<?>> scan() {
            return null;
        }
    }

    @Rule(Executable.class)
    static class AnnotatedClass1 {}

    @Rule(Executable.class)
    static class AnnotatedClass2 {}

    private GenericScanner<Rule> scanner;
    private static final String PATH_TO_SCAN = "junit.test";

    @BeforeEach
    public void init() {
        this.scanner = new GenericScannerMockImpl(PATH_TO_SCAN);
    }

    @Nested
    class SetFoundClasses {
        @Test
        void withAnnotatedClass() {
            assertDoesNotThrow(() -> scanner.setFoundClasses(new HashSet<>(Arrays.asList(AnnotatedClass1.class, AnnotatedClass2.class))));
        }

        @Test
        void withNotAnnotatedClass() {
            assertThrows(
                    IllegalArgumentException.class,
                    () -> scanner.setFoundClasses(new HashSet<>(Arrays.asList(Object.class, Objects.class)))
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
            Set<Class<?>> classes = new HashSet<>(Arrays.asList(AnnotatedClass1.class, AnnotatedClass2.class));
            scanner.setFoundClasses(classes);
            assertIterableEquals(classes, scanner.getFoundClasses());
        }
    }
}
