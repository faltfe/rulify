package de.faltfe.rulify;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "de.faltfe.rulify")
public class RulifyProperties {

    private String path = "";

}
