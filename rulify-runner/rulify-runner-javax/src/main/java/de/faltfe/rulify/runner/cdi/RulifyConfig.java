package de.faltfe.rulify.runner.cdi;

import javax.enterprise.util.Nonbinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, PARAMETER})

public @interface RulifyConfig {
    @Nonbinding String path() default "";
}
