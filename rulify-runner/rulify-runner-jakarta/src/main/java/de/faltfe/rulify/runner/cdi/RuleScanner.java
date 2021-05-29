package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.api.RuleScanner;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.Annotated;
import jakarta.enterprise.inject.spi.InjectionPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Dependent
class RuleScannerImpl {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Produces
    public RuleScanner getRuleScanner(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        if (annotated.isAnnotationPresent(RulifyConfig.class)) {
            RulifyConfig rulifyConfig = annotated.getAnnotation(RulifyConfig.class);
            log.debug("Scanned path is set to: {}", rulifyConfig.path());
            return new RuleScanner(rulifyConfig.path());
        }
        log.debug("No path to scan set. Consider using @RulifyConfig");
        return new RuleScanner("");
    }
}
