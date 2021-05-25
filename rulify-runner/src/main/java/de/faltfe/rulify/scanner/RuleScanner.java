package de.faltfe.rulify.scanner;

import de.faltfe.rulify.api.annotations.Rule;

import java.lang.annotation.Annotation;

public class RuleScanner extends AnnotationScanner{

    public RuleScanner(String path) {
        super(path);
    }

    @Override
    public Class<? extends Annotation> getAnnotationFilter() {
        return Rule.class;
    }


}
