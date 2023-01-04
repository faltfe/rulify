package de.faltfe.rulify.impl;

import de.faltfe.rulify.runner.api.annotations.Rule;
import de.faltfe.rulify.runner.api.GenericScanner;
import org.reflections.Reflections;
import org.reflections.util.ConfigurationBuilder;

import java.util.Collections;
import java.util.Set;

import static org.reflections.scanners.Scanners.SubTypes;
import static org.reflections.scanners.Scanners.TypesAnnotated;

public class RuleScanner extends GenericScanner<Rule> {
    public RuleScanner(String path) {
        super(path);
    }

    @Override
    public Set<Class<?>> scan() {
        if (this.getFoundClasses() != null) {
            return this.getFoundClasses();
        }

        Reflections reflections = new Reflections(new ConfigurationBuilder().forPackages(this.getPackageName())
                                                                            .setScanners(TypesAnnotated, SubTypes));
        this.setFoundClasses(reflections.get(TypesAnnotated.with(getAnnotationToScan()).asClass()));
        return Collections.unmodifiableSet(this.getFoundClasses());
    }
}
