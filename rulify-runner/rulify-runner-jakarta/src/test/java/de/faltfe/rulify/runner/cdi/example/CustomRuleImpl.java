package de.faltfe.rulify.runner.cdi.example;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Rule;
import jakarta.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class CustomRuleImpl extends Rule<Entity> implements CustomRule {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Entity data() {
        return new Entity(true);
    }

    @Override
    public Condition<Entity> condition() {
        return Entity::isActive;
    }

    @Override
    public Action<Entity> action() {
        return obj -> {
            obj.setName("Rule example");
            log.info("{}", obj);
        };
    }
}
