package de.faltfe.rulify.api;

import java.util.function.Predicate;

/**
 * Represents decoupled check of self defined conditions against any object.
 *
 * <p>The traditional way of writing checks was the <i>if-else</i> statement. The big downside of that is that these
 * statements are highly bounded to a class or method. In contrast to that a {@link Condition} is decoupled from any
 * related class, can be reused, is chainable and easy to test.
 *
 * <pre>{@code
 * Condition<String> isEmpty = (s) -> s.isEmpty();
 * if (isEmpty.test("String to test")) {
 *     // String was not empty
 * }
 * }</pre>
 *
 * @param <T> the type of input to the check
 * @see Predicate
 */
@FunctionalInterface
public interface Condition<T> extends Predicate<T> {
}
