package de.faltfe.rulify;

import de.faltfe.rulify.api.RulifyRunner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartupRuleRunner {

    @Inject
    private RulifyRunner runner;

    public void sayHello() {
        runner.run();
    }

}
