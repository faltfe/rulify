package io.github.faltfe.rulify.dummy;

import io.github.faltfe.rulify.runner.api.annotations.Rule;
import org.springframework.stereotype.Component;

@Component
@Rule(ExampleEffect.class)
public class ExampleRule {}
