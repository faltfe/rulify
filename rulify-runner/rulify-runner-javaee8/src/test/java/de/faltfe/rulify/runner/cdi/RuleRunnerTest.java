package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.api.RulifyRunner;
import de.faltfe.rulify.runner.cdi.example.CustomRule;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledOnJre(JRE.JAVA_8)
@EnableAutoWeld
@AddPackages({RuleRunnerImpl.class, CustomRule.class})
class RuleRunnerTest {

    @Inject
    private RulifyRunner runner;

    @Test
    void init() {
        assertNotNull(runner);
        runner.run();
    }

}
