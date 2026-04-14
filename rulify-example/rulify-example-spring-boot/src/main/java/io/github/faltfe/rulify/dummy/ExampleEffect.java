package io.github.faltfe.rulify.dummy;

import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Effect;
import io.github.faltfe.rulify.api.Modifier;
import org.springframework.stereotype.Component;

@Component
public class ExampleEffect extends Effect<ExampleRule> {
    @Override
    protected Modifier<ExampleRule> effect() {
        return e -> e;
    }

    @Override
    public ExampleRule data() {
        return new ExampleRule();
    }

    @Override
    public Condition<ExampleRule> condition() {
        return e -> true;
    }

    @Override
    public Action<ExampleRule> action() {
        return System.out::println;
    }
}
