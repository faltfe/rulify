package de.faltfe.rulify.common.conditions;

import de.faltfe.rulify.api.Condition;

public class IntegerCondition {

    public Condition<Integer> isEven = num -> num % 2 == 0;

    public Condition<Integer> isOdd = num -> num % 2 != 0;
}
