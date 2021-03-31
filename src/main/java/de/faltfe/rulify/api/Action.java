package de.faltfe.rulify.api;

import java.util.function.Consumer;

/**
 * Represents a decoupled self contained piece of logic against any object.
 *
 * <p>The traditional way of implementing logic are methods. They are actually fine except the fact that it is not
 * possible to pass them as method arguments. In contrast to classic methods a {@link Action} is decoupled from any
 * related class, can be reused, is chainable and easy to test.
 *
 * <pre>{@code
 * Action<String> append = (s) -> s.concat("World");
 * append.accept("Hello");
 * }</pre>
 *
 * <p>In case one need to perform some sort of side effects like write to the database or do something else with the
 * altered data there is also the {@link SideEffect} class.
 *
 * @param <T> the type of input on which the action is performed
 * @see Consumer
 */
@FunctionalInterface
public interface Action<T> extends Consumer<T> {
}
