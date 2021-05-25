package de.faltfe.rulify.scanner;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.concurrent.Executors;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AnnotationScanner {

    private Set<Class<?>> classes;
    private final String path;

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
