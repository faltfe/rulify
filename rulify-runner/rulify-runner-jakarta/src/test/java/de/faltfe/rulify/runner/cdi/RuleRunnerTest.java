package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.api.RulifyRunner;
import de.faltfe.rulify.runner.cdi.example.CustomRule;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;

@EnableAutoWeld
@AddPackages({RuleRunnerImpl.class, CustomRule.class})
class RuleRunnerTest {

    @Inject
    private RulifyRunner runner;

    @Test
    void init(RulifyRunner runner) {
        runner.run();
    }

}
