package de.faltfe.rulify;

import de.faltfe.rulify.runner.api.GenericScanner;
import de.faltfe.rulify.runner.api.annotations.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class RuleScanner extends GenericScanner<Rule> {

    private final RulifyProperties rulifyProperties;

    public RuleScanner(RulifyProperties rulifyProperties) {
        // FIXME
        super(rulifyProperties.getPath());
        this.rulifyProperties = rulifyProperties;

    }

    @Override
    public Set<Class<?>> scan() {
        if (!this.getFoundClasses().isEmpty()) {
            log.debug("Return cached classes");
            return this.getFoundClasses();
        }

        log.debug("Scan for classes annotated with {} in path {}", getAnnotationToScan().getSimpleName(), getPackageName());
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(getAnnotationToScan()));
        Set<BeanDefinition> beanDefs = provider.findCandidateComponents(getPackageName());
        log.debug("Found {} classes", beanDefs.size());

        Set<Class<?>> foundClasses = beanDefs.stream()
                                             .filter(Objects::nonNull)
                                             .map(BeanDefinition::getBeanClassName)
                                             .map(bean -> {
                                                 try {
                                                     return Class.forName(bean);
                                                 } catch (ClassNotFoundException e) {
                                                     throw new RuntimeException(e);
                                                 }
                                             })
                                             .collect(Collectors.toSet());
        this.setFoundClasses(Collections.unmodifiableSet(foundClasses));
        return this.getFoundClasses();
    }
}
