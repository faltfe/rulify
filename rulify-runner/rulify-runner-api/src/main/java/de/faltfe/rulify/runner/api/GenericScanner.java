package de.faltfe.rulify.runner.api;

import lombok.Getter;
import lombok.NonNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A GenericScanner is meant to be an abstraction for a possible implementation to scan for a certain {@link Annotation}
 * in the project.
 * <p>
 * The abstraction layer does not provide any logic nor implementation how to scan for annotations in the project. The
 * purpose of this class is to give an entry point for predefined or custom implementations. Some concepts are
 * <ol>
 *     <li>The generic {@code T} limit the implementation to scan for exactly one {@link Annotation}.</li>
 *     <li>The generic scanner requires to to set the package name where the scan should start.</li>
 *     <li>The abstract method {@link #scan()} is the only method which should handle the implementation.</li>
 * </ol>
 *
 * @param <T> The annotation which should be scanned for in the project.
 * @author Felix Faltin
 * @see GenericRunner
 * @since 1.0
 */
public abstract class GenericScanner<T extends Annotation> {

    /**
     * Get the annotation which the scanner should scan.
     * <p>
     * The value is inferred from the generic inside the constructor.
     */
    @Getter
    private final Class<T> annotationToScan;

    /**
     * Get the required package name where the scanner should start scanning for the {@link #annotationToScan}.
     * <p>
     * The implementation is responsible that the {@link #scan()} either starts at the packageName and finds classes
     * annotated with {@code T} recursively or scans only the given packageName.
     */
    @Getter
    private final String packageName;

    /**
     * Return classes which were found when {@link #scan()} was run.
     * <p>
     * Initially the set is to {@code Collections.emptySet()}.
     */
    @Getter
    private Set<Class<?>> foundClasses = Collections.emptySet();

    /**
     * Set classes which are contain the annotation {@link #annotationToScan}.
     * <p>
     * There is no restriction how to set the data, but it is recommended to use the return value from {@link #scan()}.
     *
     * @param classes a list of {@link Class classes} that are annotated with {@link #annotationToScan}. An
     *     {@link IllegalArgumentException} is thrown, when the passed classes contain a class which is not annotated
     *     with {@link #annotationToScan}. A {@link NullPointerException} is thrown when {@code null} is given as an
     *     argument.
     * @see #scan()
     */
    protected void setFoundClasses(@NonNull Set<Class<?>> classes) {
        Set<Class<?>> tempFoundClasses = new HashSet<>();
        for (Class<?> clazz : classes) {
            if (!clazz.isAnnotationPresent(this.annotationToScan)) {
                throw new IllegalArgumentException("The class " + clazz.getSimpleName() + " is not annotated with " + this.annotationToScan);
            }
            tempFoundClasses.add(clazz);
        }
        this.foundClasses = Collections.unmodifiableSet(tempFoundClasses);
    }

    /**
     * Create a new instance of the scanner.
     *
     * @param packageName is the string representation of the package name where the scan should start. The package
     *     name can be used in the {@link #scan()} implementation. A {@link NullPointerException} is thrown when
     *     {@code null} is given as an argument.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public GenericScanner(@NonNull String packageName) {
        this.packageName = packageName;
        this.annotationToScan = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Searching in the classpath along the {@link #packageName} for classes annotated with {@link #annotationToScan}.
     * <p>
     * It is recommended storing the scan result inside {@link #setFoundClasses(Set)} otherwise the
     * {@link #getFoundClasses()} will always return an empty set.
     *
     * @return {@link Set} filled with classes that are annotated with {@link #annotationToScan}. The return should
     *     never be {@code null}.
     */
    public abstract Set<Class<?>> scan();
}
