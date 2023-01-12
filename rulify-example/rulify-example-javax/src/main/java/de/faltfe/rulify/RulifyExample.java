package de.faltfe.rulify;

import de.faltfe.rulify.runner.api.RulifyRunner;
import de.faltfe.rulify.runner.cdi.RulifyConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RulifyExample {

    @Inject
    @RulifyConfig(path = "de.faltfe.rulify")
    private RulifyRunner rulifyRunner;

    public void run() {
        this.rulifyRunner.run();
    }
}
