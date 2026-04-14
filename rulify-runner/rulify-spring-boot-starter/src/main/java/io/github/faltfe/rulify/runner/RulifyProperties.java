package io.github.faltfe.rulify.runner;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "io.github.faltfe.rulify")
public class RulifyProperties {

    private String path = "";

}
