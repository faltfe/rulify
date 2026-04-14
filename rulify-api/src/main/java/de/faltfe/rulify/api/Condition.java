package de.faltfe.rulify.api;

import java.util.function.Predicate;

/**
 * Represents a decoupled, self-defined condition check against any object.
 *
 * <p>The traditional way of writing checks was the <i>if-else</i> statement. The big downside of that is that these
 * statements are highly bound to a class or method. In contrast, a {@link Condition} is decoupled from any
 * related class, can be reused, is chainable, and easy to test.
 *
 * <p>For conditions that need to modify data before evaluation, consider using {@link Modifier} in combination
 * with other rule constructs. For performing actions based on conditions, see {@link Rule} and {@link Effect}.
 *
 * <pre>{@code
 * Condition<String> isEmpty = (s) -> s.isEmpty();
 * if (isEmpty.test("String to test")) {
 *     // String is empty
 * }
 * }</pre>
 *
 * @param <T> the type of input to the check
 * @see Predicate
 * @see Rule
 * @see Effect
 */
@FunctionalInterface
public interface Condition<T> extends Predicate<T> {
}
