package de.faltfe.rulify.runner;

import de.faltfe.rulify.impl.RuleScanner;
import de.faltfe.rulify.runner.api.annotations.Rule;
import de.faltfe.rulify.runner.api.GenericRunner;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public class RuleRunner extends GenericRunner<Rule> {

    public RuleRunner(String packagePath) {
        super(new RuleScanner(packagePath));
    }

    @Override
    public void run() {
        start(scannedClasses -> scannedClasses.forEach(clazz -> {
            Rule rule = clazz.getAnnotation(getScanner().getAnnotationToScan());
            try {
                log.debug("Running rule {} on class {}", rule.value().getSimpleName(), clazz.getSimpleName());
                rule.value().getDeclaredConstructor().newInstance().execute();
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                log.error("Error while executing rule {}", rule.getClass().getSimpleName(), e);
                throw new IllegalStateException("Error while executing rule " + rule.getClass().getSimpleName(), e);
            }
        }));
    }
}
