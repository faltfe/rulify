package io.github.faltfe.rulify.runner.cdi;

import io.github.faltfe.rulify.runner.api.RulifyRunner;
import io.github.faltfe.rulify.runner.cdi.example.CustomRule;
import jakarta.inject.Inject;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@EnabledOnJre(JRE.JAVA_11)
@EnableAutoWeld
@AddPackages({RuleRunnerProducer.class, CustomRule.class})
class RuleRunnerTest {

    @Inject
    @RulifyConfig(path = "io.github.faltfe.rulify")
    private RulifyRunner runner;

    @Test
    void init() {
        assertNotNull(runner);
        runner.run();
    }

}
