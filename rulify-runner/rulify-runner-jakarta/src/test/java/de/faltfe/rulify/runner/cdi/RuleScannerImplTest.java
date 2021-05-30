package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.api.RuleScanner;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledOnJre(JRE.JAVA_11)
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