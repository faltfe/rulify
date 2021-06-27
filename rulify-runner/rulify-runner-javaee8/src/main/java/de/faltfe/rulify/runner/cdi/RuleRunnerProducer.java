package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.api.Executable;
import de.faltfe.rulify.api.annotations.Rule;
import de.faltfe.rulify.runner.api.RuleScanner;
import de.faltfe.rulify.runner.api.RulifyRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Singleton;

@Singleton
public class RuleRunnerProducer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private RuleScanner ruleScanner;

    @Produces
    public RulifyRunner ruleRunnerImpl(InjectionPoint injectionPoint) {
        configureRuleScanner(injectionPoint);
        return runner;
    }

    private final RulifyRunner runner = () -> ruleScanner.scanClasses().forEach(clazz -> {
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
