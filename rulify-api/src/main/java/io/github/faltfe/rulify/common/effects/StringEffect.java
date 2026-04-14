package io.github.faltfe.rulify.common.effects;

import io.github.faltfe.rulify.api.Modifier;

public class StringEffect {

    private StringEffect() {}

    public static final Modifier<String> capitalize = s -> s.substring(0,1).toUpperCase() + s.substring(1);

    public static final Modifier<String> removeWhitespaces = s -> s.replaceAll("\\s", "");
}
