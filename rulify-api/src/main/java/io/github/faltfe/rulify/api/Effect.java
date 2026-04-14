package io.github.faltfe.rulify.api;

import io.github.faltfe.rulify.internal.BaseRule;
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
 * <p>Example usage:
 * <pre>{@code
 * public class PriceAdjustmentEffect extends Effect<Order> {
 *     protected Condition<Order> condition() {
 *         return order -> order.getStatus() == OrderStatus.PENDING;
 *     }
 *
 *     protected Modifier<Order> effect() {
 *         return order -> {
 *             double newPrice = order.getPrice() * 0.9; // 10% discount
 *             order.setPrice(newPrice);
 *             return order;
 *         };
 *     }
 *
 *     protected Action<Order> action() {
 *         return order -> System.out.println("Applied discount. New price: " + order.getPrice());
 *     }
 * }
 *
 * // Usage
 * Order order = new Order(100.0, OrderStatus.PENDING);
 * new PriceAdjustmentEffect().execute(); // Modifies price and logs result
 * }</pre>
 *
 * @param <T> the type of input on that the rule is applied
 * @see Condition
 * @see Modifier
 * @see Action
 * @see Rule
 */
public abstract class Effect<T> extends BaseRule<T> {

    /**
     * Returns the modifier that will transform the input data when the condition is met.
     *
     * <p>This method should return a {@link Modifier} that defines how the input data
     * will be transformed before being passed to the {@link #action()}. The modifier
     * receives the original data and returns the transformed data.
     *
     * <p>The transformed data will then be available to the action for further processing
     * or side effects. Multiple effects can be chained where each effect receives
     * the output of the previous effect's modifier.
     *
     * @return the modifier to apply to the data when the condition is met
     */
    protected abstract Modifier<T> effect();

    /**
     * Executes this effect by evaluating the condition, applying the modifier if the condition is met,
     * and then performing the action with the modified data.
     *
     * <p>This method implements the complete effect workflow:
     * <ol>
     *   <li>Evaluate the {@link #condition()}</li>
     *   <li>If condition is {@code true}, apply the {@link #effect()} modifier to transform the data</li>
     *   <li>Execute the {@link #action()} with the transformed data</li>
     * </ol>
     *
     * <p>Note: The data used for evaluation is set during effect construction or via
     * the base class methods. Make sure to provide the appropriate data context
     * before calling this method.
     */
    public void execute() {
        if (inCase(condition())) {
            thenRun(action(), manipulate(effect()));
        }
    }

    /**
     * Applies the given modifier to the current data and returns the transformed result.
     *
     * <p>This method is used internally by the effect execution process to transform
     * the data using the provided modifier. It ensures the modifier is not null
     * before applying it.
     *
     * @param modifier the modifier to apply to the current data
     * @return the transformed data after applying the modifier
     * @throws NullPointerException if the modifier is null
     */
    protected T manipulate(Modifier<T> modifier) {
        Objects.requireNonNull(modifier);
        return modifier.apply(getData());
    }
}
