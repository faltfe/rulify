package de.faltfe.rulify.runner;

import de.faltfe.rulify.scanner.AnnotationScanner;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.function.Consumer;

@RequiredArgsConstructor
public abstract class AnnotationRunner {

    private final AnnotationScanner annotationScanner;

    public void executeRules(Consumer<Set<Class<?>>> classesConsumer) {
       classesConsumer.accept(annotationScanner.findClasses());
    }

    public abstract void run();
}
