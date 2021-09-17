package de.faltfe.rulify.runner;

import de.faltfe.rulify.runner.api.RulifyRunner;
import de.faltfe.rulify.api.annotations.Rule;
import de.faltfe.rulify.runner.api.AnnotationRunner;
import de.faltfe.rulify.runner.api.RuleScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class RuleRunner extends AnnotationRunner implements RulifyRunner {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public RuleRunner(String packagePath) {
        super(new RuleScanner(packagePath));
    }

    @Override
    public void run() {
        executeRules(scannedClasses -> scannedClasses.forEach(clazz -> {
            Rule rule = clazz.getAnnotation(Rule.class);
            try {
                log.debug("Running rule {} on class {}", rule.value().getSimpleName(), clazz.getSimpleName());
                rule.value().getDeclaredConstructor().newInstance().execute();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                log.error("Error while executing rule {}", rule.getClass().getSimpleName(), e);
            }
        }));
    }
}
