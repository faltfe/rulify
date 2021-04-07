package de.faltfe.rulify.common.effects;

import de.faltfe.rulify.api.Modifier;

public class MathEffect {

    public static Modifier<Double> square() {
        return power(2.0);
    }

    public static Modifier<Double> cube() {
        return power(3.0);
    }

    public static Modifier<Double> power(double power) {
        return value -> Math.pow(value, power);
    }

    public static Modifier<Double> sqrt() {
        return Math::sqrt;
    }

    public static Modifier<Double> root(double root) {
        return power(1.0 / root);
    }

}
