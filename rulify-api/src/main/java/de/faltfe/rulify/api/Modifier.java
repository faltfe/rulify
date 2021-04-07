package de.faltfe.rulify.api;

import java.util.function.UnaryOperator;

/**
 * Represents a decoupled self contained piece of logic which may alter the data against any object.
 * <p>
 * A modifier is a concept from functional programming paradigm. In comparison to {@link Action} a
 * {@link Modifier} will return the altered data. This allows chainable operation with the <strong>altered</strong>
 * data.
 *
 * <p>{@link Modifier} is decoupled from any related class, can be reused, is chainable and easy to test.
 *
 * <pre>{@code
 * Modifier<String> append = (s) -> s.concat("World");
 * String appendedString = append.apply("Hello");
 * }</pre>
 *
 * @param <T> The type of input on which the side effect is performed
 * @see UnaryOperator
 */
@FunctionalInterface
public interface Modifier<T> extends UnaryOperator<T> {
}
