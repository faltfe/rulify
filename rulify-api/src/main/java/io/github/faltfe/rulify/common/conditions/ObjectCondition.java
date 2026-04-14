package io.github.faltfe.rulify.common.conditions;

import io.github.faltfe.rulify.api.Condition;
import java.util.Objects;

public class ObjectCondition {

    private ObjectCondition() {}

    public static <T> Condition<T> isNull() {
        return Objects::isNull;
    }

    public static <T> Condition<T> isNotNull() {
        return Objects::nonNull;
    }
}
