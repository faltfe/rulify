package de.faltfe.rulify.runner.api;

import de.faltfe.rulify.api.annotations.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RuleScannerTest {

    @Test
    void getAnnotationFilter() {
        RuleScanner runner = new RuleScanner("");
        assertEquals(Rule.class, runner.getAnnotationFilter());
    }
}