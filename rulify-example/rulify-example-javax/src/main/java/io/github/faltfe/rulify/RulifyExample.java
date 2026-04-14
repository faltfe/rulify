package io.github.faltfe.rulify;

import io.github.faltfe.rulify.runner.api.RulifyRunner;
import io.github.faltfe.rulify.runner.cdi.RulifyConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RulifyExample {

    @Inject
    @RulifyConfig(path = "io.github.faltfe.rulify")
    private RulifyRunner rulifyRunner;

    public void run() {
        this.rulifyRunner.run();
    }
}
