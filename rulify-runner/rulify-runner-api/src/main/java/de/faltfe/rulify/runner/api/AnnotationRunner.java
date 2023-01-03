package de.faltfe.rulify.runner.api;

import java.util.Set;
import java.util.function.Consumer;

public abstract class AnnotationRunner {

    private final AnnotationScanner annotationScanner;

    protected AnnotationRunner(AnnotationScanner annotationScanner) {this.annotationScanner = annotationScanner;}

    /**
     *
     * @param classesConsumer
     */
    public void executeRules(Consumer<Set<Class<?>>> classesConsumer) {
        classesConsumer.accept(annotationScanner.scanClasses());
    }
}
