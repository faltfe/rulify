package de.faltfe.rulify.api;

import de.faltfe.rulify.internal.BaseRule;

/**
 * A rule is an abstract concept of an <i>if-else</i> statement using a {@link Condition} and an {@link Action}.
 *
 * <p>Whenever {@link #execute()} is called, the provided {@link Condition} is evaluated. Only if the condition
 * evaluates to {@code true} the provided {@link Action} is performed.
 *
 * <p>If one needs to perform an {@link Action} to altered data or altered passed data before, there is also
 * {@link Effect} available.
 *
 * @param <T> the type of input on that the rule is applied
 */
public abstract class Rule<T> extends BaseRule<T> {

    /**
     * Whenever this method is called the implemented {@link #condition()} is evaluated.
     * Only if the result is {@code true} the provided {@link #action()}
     * implementation is executed.
     */
    public void execute() {
        if (inCase(condition())) {
            thenRun(action());
        }
    }
}
