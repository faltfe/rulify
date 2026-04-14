package io.github.faltfe.rulify.runner.cdi;

import io.github.faltfe.rulify.api.Executable;
import io.github.faltfe.rulify.runner.api.annotations.Rule;
import io.github.faltfe.rulify.impl.RuleScanner;
import io.github.faltfe.rulify.runner.api.RulifyRunner;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.Annotated;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

@Singleton
@Slf4j
public class RuleRunnerProducer {

    private RuleScanner ruleScanner;

    @Produces
    public RulifyRunner ruleRunnerImpl(InjectionPoint injectionPoint) {
        configureRuleScanner(injectionPoint);
        return runner;
    }

    private final RulifyRunner runner = () -> ruleScanner.scan().forEach(clazz -> {
        Rule rule = clazz.getAnnotation(Rule.class);
        log.debug("Running rule {} on class {}", rule.value().getSimpleName(), clazz.getSimpleName());
        Executable executable = CDI.current().select(rule.value()).get();
        executable.execute();
        log.debug("Finished rule for {}", clazz.getSimpleName());
    });

    private void configureRuleScanner(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        if (annotated.isAnnotationPresent(RulifyConfig.class)) {
            RulifyConfig rulifyConfig = annotated.getAnnotation(RulifyConfig.class);
            log.info("Scanned path is set to: {}", rulifyConfig.path());
            ruleScanner = new RuleScanner(rulifyConfig.path());
        } else {
            log.info("No path to scan set. Consider using @RulifyConfig");
            ruleScanner = new RuleScanner("");
        }
    }

}
