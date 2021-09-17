package de.faltfe.rulify.common.conditions;

import de.faltfe.rulify.api.Condition;

public class IntegerCondition {

    private IntegerCondition() {}

    public static final Condition<Integer> isEven = num -> num % 2 == 0;

    public static final Condition<Integer> isOdd = num -> num % 2 != 0;
}
