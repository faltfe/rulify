package io.github.faltfe.rulify.dummy;

import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Effect;
import io.github.faltfe.rulify.api.Modifier;
import io.github.faltfe.rulify.common.conditions.BoolCondition;

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
