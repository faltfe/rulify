package de.faltfe.rulify.api.annotations;

import de.faltfe.rulify.api.Executable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Rule {
    Class<? extends Executable>[] value();
}
