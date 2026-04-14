package de.faltfe.rulify.api;

import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

/**
 * Represents a decoupled, self-contained piece of logic which may alter data and return the modified result.
 * <p>
 * A modifier is a concept from the functional programming paradigm. In comparison to an {@link Action}, a
 * {@link Modifier} will return the altered data. This allows for chainable operations with the <strong>altered</strong>
 * data.
 *
 * <p>A {@link Modifier} is decoupled from any related class, can be reused, is chainable, and easy to test.
 *
 * <p>For performing side effects without returning data, consider using {@link Action}. For conditional
 * data modification followed by actions, see {@link Effect}.
 *
 * <pre>{@code
 * Modifier<String> append = (s) -> s.concat("World");
 * String appendedString = append.apply("Hello");
 * }</pre>
 *
 * @param <T> the type of input on which the modification is performed
 * @see UnaryOperator
 * @see Action
 * @see Effect
 */
@FunctionalInterface
public interface Modifier<T> extends UnaryOperator<T> {

    /**
     * Returns a new modifier that applies this modifier and then executes a side effect with both
     * the original and transformed values.
     *
     * <p>This method enables "tapping" into the modification process to perform side effects
     * (like logging, auditing, or debugging) without affecting the main data flow. The consumer
     * receives both the input value and the output value for inspection.
     *
     * <p>Example usage:
     * <pre>{@code
     * Modifier<String> upperCaseWithLogging = Modifier.<String>identity()
     *     .tap((input, output) -> System.out.println("Transformed: " + input + " -> " + output))
     *     .andThen(String::toUpperCase);
     * }</pre>
     *
     * @param consumer a consumer that receives the original input and the transformed output
     * @return a new modifier that applies this modifier and executes the side effect
     */
    default Modifier<T> tap(BiConsumer<T, T> consumer) {
        return v -> {
            T t = apply(v);
            consumer.accept(v, t);
            return t;
        };
    }

}
