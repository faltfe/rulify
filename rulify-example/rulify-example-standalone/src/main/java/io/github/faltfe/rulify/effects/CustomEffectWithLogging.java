package io.github.faltfe.rulify.effects;

import io.github.faltfe.rulify.Entity;
import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Effect;
import io.github.faltfe.rulify.api.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEffectWithLogging extends Effect<Entity> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Modifier<Entity> modifier = obj -> new Entity(true);

    @Override
    protected Modifier<Entity> effect() {
        return modifier.tap((in, out) -> {
            log.info("Input {}", in);
            log.info("Output {}", out);
        });
    }

    @Override
    public Entity data() {
        return new Entity(false);
    }

    @Override
    public Condition<Entity> condition() {
        return obj -> !obj.isActive();
    }

    @Override
    public Action<Entity> action() {
        return obj -> {
            obj.setName("Effect example");
            log.info("{}", obj);
        };
    }
}
