package de.faltfe.rulify.dummy;

import de.faltfe.rulify.runner.api.annotations.Rule;
import org.springframework.stereotype.Component;

@Component
@Rule(ExampleEffect.class)
public class ExampleRule {}
