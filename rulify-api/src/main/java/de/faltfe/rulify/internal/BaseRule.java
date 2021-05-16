package de.faltfe.rulify.internal;

import de.faltfe.rulify.api.Action;
import de.faltfe.rulify.api.Condition;
import de.faltfe.rulify.api.Executable;

import java.util.Objects;

public abstract class BaseRule<T> implements Executable {

    private T object;

    /**
     * Provide data for all other operations.
     * <p>
     * The provided data can basically be any object that satisfies the return
     * type. There is no need to worry about multiple calls of this method
     * because the value will be cached internally.
     * <p>
     * The data is used in {@link #condition()} and {@link #action()}.
     *
     * @return original pure data that is never {@code null}
     */
    public abstract T data();

    /**
     * Provide any {@link Condition} that is checked against the provided {@link #data()}.
     * <p>
     * A condition can validate any logic which evaluate to {@code true} or {@code false}.
     * The only limitation is that the validated object <strong>must not</strong>
     * be a primitive type.
     * <p>
     * For example a condition may validate if an integer is odd or even. If the integer is even the condition is
     * fulfilled.
     *
     * <pre>{@code
     * protected Condition<Integer> condition() {
     *   return i -> i % 2 == 0;
     * }
     * }</pre>
     *
     * @return a reference to the {@link Condition } implementation or the implementation itself
     * @see #inCase(Condition)
     */
    public abstract Condition<T> condition();

    /**
     * Provide any {@link Action} that performs logic against the provided {@link #data()}.
     * <p>
     * The action can run any logic against the provided data. Multiple actions
     * can be joined. To each joined action the <strong>original</strong> data
     * is passed.
     *
     * @return a reference to the {@link Action} implementation or the implementation itself
     * @see #thenRun(Action)
     */
    public abstract Action<T> action();

    /**
     * Get the data one which all operations are performed.
     * <p>
     * The returned data is provided by the implementation of {@link #data()}.
     * To prevent multiple calls of {@link #data()} caching is enabled.
     *
     * @return the passed {@link #data()}
     */
    protected T getObject() {
        if (object == null) {
            this.object = data();
        }
        return object;
    }

    /**
     * Evaluate a passed {@link Condition}.
     * <p>
     * The passed condition must not be {@code null}.
     *
     * @param condition any {@link Condition} run against the provided {@link #data()}
     * @return {@code true} if the condition is fulfilled.
     */
    protected boolean inCase(Condition<T> condition) {
        Objects.requireNonNull(condition);
        return condition.test(getObject());
    }

    /**
     * Run the passed {@link Action} on the provided {@link #data()}.
     * <p>
     * The passed condition must not be {@code null}.
     *
     * @param action any valid {@link Action} run on the provided data.
     * @see #thenRun(Action, Object)
     */
    protected void thenRun(Action<T> action) {
        this.thenRun(action, getObject());
    }

    /**
     * Run a passed {@link Action} on the passed data.
     * <p>
     * The passed condition must not be {@code null}.
     *
     * @param action any valid {@link Action} run on the provided data.
     * @param data   provide the value for the action
     * @see #thenRun(Action)
     */
    protected void thenRun(Action<T> action, T data) {
        Objects.requireNonNull(action);
        action.accept(data);
    }
}
