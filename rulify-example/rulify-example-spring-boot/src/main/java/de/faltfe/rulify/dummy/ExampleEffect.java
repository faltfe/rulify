package de.faltfe.rulify.dummy;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Effect;
import de.faltfe.rulify.api.Modifier;
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
