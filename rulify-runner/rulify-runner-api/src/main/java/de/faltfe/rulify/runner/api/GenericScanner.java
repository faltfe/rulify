package de.faltfe.rulify.runner.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;

public class GenericScanner<T extends Annotation> extends AnnotationScanner {

    private final Class<T> annotationToScan;

    @SuppressWarnings({"rawtypes", "unchecked"})
    public GenericScanner(String path) {
        super(path);
        this.annotationToScan = (Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Class<T> getAnnotationFilter() {
        return annotationToScan;
    }
}
