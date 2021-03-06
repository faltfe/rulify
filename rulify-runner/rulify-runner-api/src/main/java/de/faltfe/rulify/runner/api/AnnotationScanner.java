package de.faltfe.rulify.runner.api;

import org.reflections8.Reflections;
import org.reflections8.scanners.SubTypesScanner;
import org.reflections8.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.concurrent.Executors;

abstract class AnnotationScanner {

    private Set<Class<?>> classes;
    private final String path;

    protected AnnotationScanner(String path) {
        this.path = path;
    }

    public abstract Class<? extends Annotation> getAnnotationFilter();

    public Set<Class<?>> scanClasses() {
        if (classes != null) {
            return classes;
        }
        Reflections reflections = new Reflections(path, new TypeAnnotationsScanner(), new SubTypesScanner(), Executors.newFixedThreadPool(Runtime
                .getRuntime()
                .availableProcessors()));
        this.classes = reflections.getTypesAnnotatedWith(getAnnotationFilter());
        return classes;
    }
}
