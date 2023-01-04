package de.faltfe.rulify.runner.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.function.Consumer;

/**
 * A Generic Runner is an abstract layer for an implementation that uses a {@link GenericScanner} to find classes
 * annotated with {@link GenericScanner#getAnnotationToScan()}.
 *
 * @author Felix Faltin
 * @see RulifyRunner
 * @see GenericScanner
 * @since 1.0
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class GenericRunner<T extends Annotation> implements RulifyRunner {

    /**
     * The scanner holds references to the annotation, the package name and the scanned classes which where found.
     *
     * @see GenericScanner
     */
    @Getter
    private final GenericScanner<T> scanner;

    /**
     * Start the runner by evaluate the given consumer and provide the result of {@link GenericScanner#scan()} as an
     * argument.
     *
     * @param classesConsumer is a consumer that handles what should happen to the classes provided by
     *     {@link GenericScanner#scan()}.
     */
    protected void start(@NonNull Consumer<Set<Class<?>>> classesConsumer) {
        classesConsumer.accept(scanner.scan());
    }
}
