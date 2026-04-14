package io.github.faltfe.rulify.runner.cdi.example;

import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Named;

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
