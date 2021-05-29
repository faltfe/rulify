package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.api.RuleScanner;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnableAutoWeld
@AddPackages(RuleScannerImpl.class)
class RuleScannerImplTest {

    @Inject
    private RuleScanner ruleScanner;

    @Test
    void scannerNotNull() {
        assertNotNull(ruleScanner);
    }

}