package de.faltfe.rulify.api;

import de.faltfe.rulify.internal.BaseRule;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * An effect is a more complex concept of an <i>if-else</i> statement using a {@link Condition}, run a
 * {@link Modifier} and an {@link Action} afterwards.
 *
 * <p>Whenever {@link #execute()} is called, the provided {@link Condition} is evaluated. Only if the condition
 * evaluates to {@code true} the provided {@link Modifier} is performed and the returned value is passed as data to
 * the {@link Action}.
 *
 * <p>If one needs to perform an {@link Action} without the need of altering data, there is also {@link Rule} available.
 *
 * @param <T> the type of input on that the rule is applied
 */
public abstract class Effect<T> extends BaseRule<T> {

    /**
     * An effect will alter the passed data.
     * <p>
     * The idea of a {@link Modifier} is that the provided data is altered
     * <strong>before</strong> it gets passed to an @{@link Action}.
     * <p>
     * Multiple effects can be joined. Only to the first effect the original data
     * will be passed. All subsequent effects get the data from the previous effect.
     *
     * @return the altered data
     */
    protected abstract @NotNull Modifier<T> effect();

    /**
     * Whenever the method is called, the provided {@link Condition} is evaluated.
     * Only if the condition evaluates to {@code true} the provided {@link Modifier}
     * is performed and the returned value is passed as data to the {@link Action}.
     */
    public void execute() {
        if (inCase(condition())) {
            thenRun(action(), manipulate(effect()));
        }
    }

    /**
     * Manipulate the provided {@link #data()}.
     *
     * @param modifier any valid {@link Modifier}
     * @return the manipulated data which is never {@code null}.
     */
    protected @NotNull T manipulate(@NotNull Modifier<T> modifier) {
        Objects.requireNonNull(modifier);
        return modifier.apply(getObject());
    }
}
