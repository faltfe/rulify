package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.api.Executable;
import de.faltfe.rulify.api.annotations.Rule;
import de.faltfe.rulify.runner.api.RuleScanner;
import de.faltfe.rulify.runner.api.RulifyRunner;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Singleton
public class RuleRunnerImpl implements RulifyRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Inject
    @RulifyConfig(path = "de.faltfe.rulify")
    private RuleScanner ruleScanner;

    @Override
    public void run() {
        ruleScanner.scanClasses().forEach(clazz -> {
            Rule rule = clazz.getAnnotation(Rule.class);
            log.debug("Running rule {} on class {}", rule.value().getSimpleName(), clazz.getSimpleName());
            Executable executable = CDI.current().select(rule.value()).get();
            executable.execute();
            log.debug("Finished rule for {}", clazz.getSimpleName());
        });
    }
}
