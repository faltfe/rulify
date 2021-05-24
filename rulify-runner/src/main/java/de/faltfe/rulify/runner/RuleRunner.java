package de.faltfe.rulify.runner;

import de.faltfe.rulify.api.annotations.Rule;
import de.faltfe.rulify.scanner.RuleScanner;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class RuleRunner extends AnnotationRunner {

    public RuleRunner(String packagePath) {
        super(new RuleScanner(packagePath));
    }

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