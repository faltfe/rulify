package de.faltfe.rulify.scanner;

import de.faltfe.rulify.api.annotations.Rule;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Slf4j
@Data
public class RuleRunner {

    private final String path;
    private Set<Class<?>> classes;

    public Set<Class<?>> findClasses() {
        if (classes == null) {
            Reflections reflections = new Reflections(path);
            this.classes = reflections.getTypesAnnotatedWith(Rule.class);
        }
        return classes;
    }

    public void executeRules() {
        findClasses().forEach(clazz -> {
            Rule rule = clazz.getAnnotation(Rule.class);
            try {
                log.debug("Running rule {} on class {}", rule.getClass().getSimpleName(), clazz.getSimpleName());
                rule.value().getDeclaredConstructor().newInstance().execute();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.error("Error while executing rule {}", rule.getClass().getSimpleName(), e);
            }
        });
    }
}
