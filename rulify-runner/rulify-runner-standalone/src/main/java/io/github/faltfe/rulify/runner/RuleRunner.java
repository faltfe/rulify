package io.github.faltfe.rulify.runner;

import io.github.faltfe.rulify.impl.RuleScanner;
import io.github.faltfe.rulify.runner.api.GenericRunner;
import io.github.faltfe.rulify.runner.api.annotations.Rule;
import java.lang.reflect.InvocationTargetException;
import lombok.extern.slf4j.Slf4j;

/**
 * Standalone implementation of {@link io.github.faltfe.rulify.runner.api.RulifyRunner} that scans for classes annotated
 * with {@link Rule} and executes their associated {@link io.github.faltfe.rulify.api.Executable} instances.
 */
@Slf4j
public class RuleRunner extends GenericRunner<Rule> {

    /**
     * Creates a new RuleRunner that scans the specified package path for rules.
     *
     * @param packagePath the package path to scan for rule classes
     */
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
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                     InvocationTargetException e) {
                log.error("Error while executing rule {}", clazz.getSimpleName(), e);
                throw new IllegalStateException("Error while executing rule " + clazz.getSimpleName(), e);
            }
        }));
    }
}
