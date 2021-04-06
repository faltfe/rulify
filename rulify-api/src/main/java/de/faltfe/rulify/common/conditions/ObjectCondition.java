package de.faltfe.rulify.common.conditions;

import de.faltfe.rulify.api.Condition;

import java.util.Objects;

public class ObjectCondition {

    public <T> Condition<T> isNull() {
        return Objects::isNull;
    }

    public <T> Condition<T> isNotNull() {
        return Objects::nonNull;
    }
}
