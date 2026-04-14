package io.github.faltfe.rulify.effects;

import io.github.faltfe.rulify.Entity;
import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Effect;
import io.github.faltfe.rulify.api.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomEffect extends Effect<Entity> {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected Modifier<Entity> effect() {
        return obj -> new Entity(true);
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
