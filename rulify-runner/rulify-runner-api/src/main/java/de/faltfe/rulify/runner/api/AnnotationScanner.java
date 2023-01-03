package de.faltfe.rulify.runner.api;

import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

/**
 *
 */
abstract class AnnotationScanner {

    private Set<Class<?>> classes;
    private final String path;

    protected AnnotationScanner(String path) {
        this.path = path;
    }

    /**
     *
     * @return
     */
    public abstract Class<? extends Annotation> getAnnotationFilter();

    /**
     *
     * @return
     */
    public Set<Class<?>> scanClasses() {
        if (classes != null) {
            return classes;
        }

        Reflections reflections = new Reflections(
            new ConfigurationBuilder().forPackage(this.path)
                                      .setScanners(TypesAnnotated, SubTypes)
        );
        this.classes = reflections.get(TypesAnnotated.with(getAnnotationFilter()).asClass());

        return classes;
    }
}
