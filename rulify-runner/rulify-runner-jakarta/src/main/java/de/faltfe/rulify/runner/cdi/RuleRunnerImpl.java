package de.faltfe.rulify.runner.cdi;

import de.faltfe.rulify.runner.RuleRunner;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.spi.Annotated;
import jakarta.enterprise.inject.spi.InjectionPoint;
import jakarta.inject.Singleton;
import org.slf4j.LoggerFactory;

@Singleton
public class RuleRunnerImpl {

    private final org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

    @Produces
    public RuleRunner getRuleRunner(InjectionPoint injectionPoint) {
        Annotated annotated = injectionPoint.getAnnotated();
        String path = "";
        if (annotated.isAnnotationPresent(Rulify.class)) {
            Rulify rulifyAnnotation = annotated.getAnnotation(Rulify.class);
            path = rulifyAnnotation.path();
        }
        logger.debug("Scanned path is set to {}", path);
        return new RuleRunner(path);
    }
}
