package io.github.faltfe.rulify.impl;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import io.github.faltfe.rulify.runner.api.GenericScanner;
import io.github.faltfe.rulify.runner.api.annotations.Rule;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of {@link GenericScanner} that uses the ClassGraph library to scan for classes annotated with
 * {@link Rule} in the specified package.
 */
public class RuleScanner extends GenericScanner<Rule> {

    /**
     * Creates a new RuleScanner for the given package path.
     *
     * @param path the package path to scan for rule classes
     */
    public RuleScanner(String path) {
        super(path);
    }

    @Override
    public Set<Class<?>> scan() {
        if (this.getFoundClasses() != null && !this.getFoundClasses().isEmpty()) {
            return this.getFoundClasses();
        }

        try (ScanResult scanResult = new ClassGraph().enableClassInfo()
                                                     .enableAnnotationInfo()
                                                     .acceptPackages(this.getPackageName())
                                                     .scan()) {
            Set<Class<?>> classes = new HashSet<>(
                scanResult.getClassesWithAnnotation(getAnnotationToScan()).loadClasses()
            );
            this.setFoundClasses(classes);
        }
        return Collections.unmodifiableSet(this.getFoundClasses());
    }
}
