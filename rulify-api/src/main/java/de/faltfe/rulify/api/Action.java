package de.faltfe.rulify.api;

import java.util.function.Consumer;

/**
 * Represents a decoupled, self-contained piece of logic that operates on any object.
 *
 * <p>The traditional way of implementing logic is through methods. They are actually fine except for the fact that it is not
 * possible to pass them as method arguments. In contrast to classic methods, an {@link Action} is decoupled from any
 * related class, can be reused, is chainable, and easy to test.
 *
 * <pre>{@code
 * Action<String> append = (s) -> s.concat("World");
 * append.accept("Hello");
 * }</pre>
 *
 * <p>In case you need to perform some sort of side effects like writing to the database or performing other operations
 * with altered data, there is also the {@link Modifier} class. For conditional execution of actions, see {@link Rule}
 * and {@link Effect}.
 *
 * @param <T> the type of input on which the action is performed
 * @see Consumer
 * @see Modifier
 * @see Rule
 * @see Effect
 */
@FunctionalInterface
public interface Action<T> extends Consumer<T> {
}
