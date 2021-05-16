package de.faltfe.rulify.effects;

import de.faltfe.rulify.Entity;
import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Effect;
import de.faltfe.rulify.api.Modifier;
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
