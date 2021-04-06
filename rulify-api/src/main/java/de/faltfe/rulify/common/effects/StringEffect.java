package de.faltfe.rulify.common.effects;

import de.faltfe.rulify.api.SideEffect;

public class StringEffect {

    public SideEffect<String> capitalize = s -> s.substring(0,1).toUpperCase() + s.substring(1);

    public SideEffect<String> removeWhitespaces = s -> s.replaceAll("\\s", "");
}
