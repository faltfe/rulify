package io.github.faltfe.rulify.runner.api.annotations;

import io.github.faltfe.rulify.api.Executable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark a class as a rule that should be executed by the
 * {@link io.github.faltfe.rulify.runner.api.RulifyRunner}. The value specifies the {@link Executable} implementation
 * that contains the rule logic.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {

    /**
     * Returns the {@link Executable} implementation that contains the rule logic.
     *
     * @return the Executable class to execute for this rule.
     */
    Class<? extends Executable> value();
}
