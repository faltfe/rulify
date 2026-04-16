package io.github.faltfe.rulify.impl;

import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class RuleScannerTest {

    @Test
    void scanWithValidPath() {
        RuleScanner scannerSpy = spy(new RuleScanner("io.github.faltfe.rulify"));
        Set<Class<?>> classes = scannerSpy.scan();
        assertEquals(1, classes.size());

        // Cached values should be uses
        scannerSpy.scan();
        verify(scannerSpy).getAnnotationToScan();
    }

    @Test
    void scanWithInvalidPath() {
        RuleScanner scanner = new RuleScanner("path.does.not.exists");
        Set<Class<?>> classes = scanner.scan();
        assertTrue(classes.isEmpty());
    }
}
