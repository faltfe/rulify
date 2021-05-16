package de.faltfe.rulify.common.effects;

import de.faltfe.rulify.api.Modifier;

public class StringEffect {

    public static Modifier<String> capitalize = s -> s.substring(0,1).toUpperCase() + s.substring(1);

    public static Modifier<String> removeWhitespaces = s -> s.replaceAll("\\s", "");
}
