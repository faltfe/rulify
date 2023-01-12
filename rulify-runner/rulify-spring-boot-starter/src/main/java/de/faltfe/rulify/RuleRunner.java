package de.faltfe.rulify;

import de.faltfe.rulify.api.Executable;
import de.faltfe.rulify.runner.api.GenericRunner;
import de.faltfe.rulify.runner.api.GenericScanner;
import de.faltfe.rulify.runner.api.annotations.Rule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

@Slf4j
public class RuleRunner extends GenericRunner<Rule> {

    private final ApplicationContext ctx;
    public RuleRunner(GenericScanner<Rule> scanner, ApplicationContext ctx) {
        super(scanner);
        this.ctx = ctx;
    }

    @Override
    public void run() {
        start((scannedClasses) -> {
            scannedClasses.forEach((clazz -> {
                Rule rule = clazz.getAnnotation(Rule.class);
                log.debug("Running rule {} on class {}", rule.getClass()
                                                             .getSimpleName(), clazz.getSimpleName());
                Executable executable = ctx.getBean(rule.value(), Executable.class);
                executable.execute();
                log.trace("Finished rule {} on class {}", rule.getClass()
                                                              .getSimpleName(), clazz.getSimpleName());

            }));
        });

    }
}
