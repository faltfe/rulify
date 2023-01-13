package de.faltfe.rulify.impl;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class RuleScannerTest {

    @Test
    void scanWithValidPath() {
        RuleScanner scannerSpy = spy(new RuleScanner("de.faltfe.rulify"));
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
