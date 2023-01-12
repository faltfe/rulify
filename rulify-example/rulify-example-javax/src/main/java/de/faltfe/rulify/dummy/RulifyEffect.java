package de.faltfe.rulify.dummy;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Effect;
import de.faltfe.rulify.api.Modifier;
import de.faltfe.rulify.common.conditions.BoolCondition;

import javax.inject.Named;

@Named
public class RulifyEffect extends Effect<RulifyRule> {
    @Override
    protected Modifier<RulifyRule> effect() {
        return e -> e;
    }

    @Override
    public RulifyRule data() {
        return new RulifyRule();
    }

    @Override
    public Condition<RulifyRule> condition() {
        return BoolCondition.alwaysTrue();
    }

    @Override
    public Action<RulifyRule> action() {
        return System.out::println;
    }
}
