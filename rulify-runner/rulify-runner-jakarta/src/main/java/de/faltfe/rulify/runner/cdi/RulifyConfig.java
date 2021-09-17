package de.faltfe.rulify.runner.cdi;

import jakarta.enterprise.util.Nonbinding;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER})

public @interface RulifyConfig {
    @Nonbinding String path() default "";
}
