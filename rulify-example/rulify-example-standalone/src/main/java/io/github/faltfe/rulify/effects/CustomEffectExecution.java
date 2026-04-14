package io.github.faltfe.rulify.effects;

import io.github.faltfe.rulify.api.Executable;

public class CustomEffectExecution {
    public static void main(String[] args) {
        Executable effect = new CustomEffect();
        effect.execute();

        Executable effectWithLogging = new CustomEffectWithLogging();
        effectWithLogging.execute();
    }
}
