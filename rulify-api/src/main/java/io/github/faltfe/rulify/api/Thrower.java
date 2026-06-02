package io.github.faltfe.rulify.api;

import io.github.faltfe.rulify.internal.BaseRule;

/**
 * A rule that throws an exception when its {@link Condition} is matched.
 *
 * <p>Whenever {@link #execute()} is called, the {@link #condition()} is evaluated.
 * If it returns {@code true}, the runtime exception produced by
 * {@link #exception(Object)} is thrown.
 *
 * <p>Otherwise, the rule does nothing.
 *
 * <pre>{@code
 * public class RejectOrder extends Thrower<Order> {
 *     protected Condition<Order> condition() {
 *         return order -> order.getAmount() > 10000;
 *     }
 *
 *     protected RuntimeException exception(Order order) {
 *         return new IllegalStateException("Order exceeds limit: " + order.getAmount());
 *     }
 * }
 *
 * Order order = new Order(15000);
 * new RejectOrder().execute(); // throws IllegalStateException when the rule matches
 * }</pre>
 *
 * @param <T> the type of input on which the rule is applied
 */
public abstract class Thrower<T> extends BaseRule<T> {

    /**
     * Provide the exception that should be thrown when the {@link #condition()} is fulfilled.
     *
     * @param data the current rule data
     * @return the runtime exception to throw
     */
    protected abstract RuntimeException exception(T data);

    /**
     * Executes this thrower by evaluating the condition and throwing the configured exception
     * if the condition is fulfilled.
     */
    public void execute() {
        if (inCase(condition())) {
            throw exception(getData());
        }
    }
}
