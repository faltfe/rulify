package de.faltfe.rulify.common.conditions;

import de.faltfe.rulify.api.Condition;

public class StringCondition {

    public Condition<String> isEmpty = String::isEmpty;
}
