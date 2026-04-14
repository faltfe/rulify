package io.github.faltfe.rulify.common.conditions;

import io.github.faltfe.rulify.api.Condition;

public class BoolCondition {

    private BoolCondition() {}

    public static <T> Condition<T> alwaysTrue() {
        return obj -> true;
    }

    public static <T> Condition<T> alwaysFalse() {
        return obj -> false;
    }
}
