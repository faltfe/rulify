package de.faltfe.rulify.config;

import de.faltfe.rulify.RuleRunner;
import de.faltfe.rulify.RuleScanner;
import de.faltfe.rulify.RulifyProperties;
import de.faltfe.rulify.runner.api.RulifyRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(RulifyRunner.class)
@EnableConfigurationProperties(RulifyProperties.class)
@Slf4j
public class SpringRulifyAutoConfiguration {

    private RuleRunner ruleRunner;
    private final ApplicationContext ctx;
    private final RulifyProperties rulifyProperties;

    public SpringRulifyAutoConfiguration(RulifyProperties rulifyProperties, ApplicationContext ctx) {
        this.rulifyProperties = rulifyProperties;
        this.ctx = ctx;
    }

    @Bean
    @ConditionalOnMissingBean
    public RulifyRunner rulifyRunner() {
        return getRuleRunner();
    }

    private RuleRunner getRuleRunner() {
        if (this.ruleRunner == null) {
            this.ruleRunner = new RuleRunner(new RuleScanner(this.rulifyProperties), ctx);
        }
        return this.ruleRunner;
    }
}
