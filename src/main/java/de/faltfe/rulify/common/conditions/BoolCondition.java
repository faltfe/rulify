package de.faltfe.rulify.common.conditions;

import de.faltfe.rulify.api.Condition;

public class BoolCondition {
    public <T> Condition<T> alwaysTrue() {
       return obj -> true;
    }

    public <T> Condition<T> alwaysFalse() {
        return obj -> false;
    }
}
