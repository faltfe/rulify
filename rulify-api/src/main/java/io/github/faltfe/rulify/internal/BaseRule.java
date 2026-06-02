package io.github.faltfe.rulify.internal;

import io.github.faltfe.rulify.api.Action;
import io.github.faltfe.rulify.api.Condition;
import io.github.faltfe.rulify.api.Executable;
import java.util.Objects;
import org.jspecify.annotations.Nullable;
import java.util.function.Supplier;

public abstract class BaseRule<T> implements Executable {

    private @Nullable T data;

    /**
     * Provide data for all other operations.
     * <p>
     * The provided data can basically be any object that satisfies the return type. There is no need to worry about
     * multiple calls of this method because the value will be cached internally.
     * <p>
     * The data is used in {@link #condition()} and {@link #action()}.
     *
     * @return original pure data that is never {@code null}
     */
    public abstract T data();

    /**
     * Provide any {@link Condition} that is checked against the provided {@link #data()}.
     * <p>
     * A condition can validate any logic which evaluate to {@code true} or {@code false}. The only limitation is that
     * the validated object <strong>must not</strong> be a primitive type.
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
     * The action can run any logic against the provided data. Multiple actions can be joined. To each joined action
     * the
     * <strong>original</strong> data is passed.
     *
     * @return a reference to the {@link Action} implementation or the implementation itself
     * @see #thenRun(Action)
     */
    public abstract Action<T> action();

    /**
     * Executes this rule or effect using the provided data supplier.
     * <p>
     * The supplier is only invoked for this execution and its value is cached for the rest of the rule/effect
     * lifecycle. This allows data to be supplied from the outside once for both
     * {@link io.github.faltfe.rulify.api.Rule} and {@link io.github.faltfe.rulify.api.Effect} implementations.
     *
     * @param dataSupplier provider for the data used by {@link #condition()} and {@link #action()}
     * @throws NullPointerException if {@code dataSupplier} or its returned value is null
     */
    public void execute(Supplier<T> dataSupplier) {
        this.data = Objects.requireNonNull(dataSupplier.get(), "supplied data must not be null");
        this.execute();
    }

    /**
     * Get the data one which all operations are performed.
     * <p>
     * The returned data is provided by the implementation of {@link #data()}. To prevent multiple calls of
     * {@link #data()} caching is enabled.
     *
     * @return the passed {@link #data()}
     */
    protected T getData() {
        if (data == null) {
            this.data = data();
        }
        return data;
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
        return condition.test(getData());
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
        this.thenRun(action, getData());
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
