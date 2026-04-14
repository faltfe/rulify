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
 * <p>Example usage:
 * <pre>{@code
 * public class DiscountRule extends Rule<Order> {
 *     protected Condition<Order> condition() {
 *         return order -> order.getTotal() > 100.0;
 *     }
 *
 *     protected Action<Order> action() {
 *         return order -> order.applyDiscount(0.1); // 10% discount
 *     }
 * }
 *
 * // Usage
 * Order order = new Order(150.0);
 * new DiscountRule().execute(); // Applies discount if total > 100
 * }</pre>
 *
 * @param <T> the type of input on that the rule is applied
 * @see Condition
 * @see Action
 * @see Effect
 */
public abstract class Rule<T> extends BaseRule<T> {

    /**
     * Executes this rule by evaluating the condition and performing the action if the condition is met.
     *
     * <p>This method implements the core rule logic: first check the {@link #condition()},
     * and if it returns {@code true}, execute the {@link #action()}.
     *
     * <p>Note: The data used for evaluation is set during rule construction or via
     * the base class methods. Make sure to provide the appropriate data context
     * before calling this method.
     */
    public void execute() {
        if (inCase(condition())) {
            thenRun(action());
        }
    }
}
