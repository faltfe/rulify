package io.github.faltfe.rulify.api;

/**
 * Represents an executable unit of logic that can be invoked without parameters.
 *
 * <p>This interface is designed for scenarios where you need to encapsulate and defer
 * the execution of some logic. It's particularly useful for creating reusable execution
 * blocks that can be passed around and invoked at the appropriate time.
 *
 * <p>Common use cases include:
 * <ul>
 *   <li>Deferred execution of business logic</li>
 *   <li>Callback mechanisms</li>
 *   <li>Command pattern implementations</li>
 *   <li>Rule execution in rule engines</li>
 * </ul>
 *
 * <pre>{@code
 * Executable sendEmail = () -> emailService.sendNotification();
 * Executable logEvent = () -> logger.info("Event occurred");
 *
 * // Execute later
 * sendEmail.execute();
 * logEvent.execute();
 * }</pre>
 *
 * @see Rule
 * @see Effect
 * @see Action
 */
@FunctionalInterface
public interface Executable {

    /**
     * Executes the encapsulated logic.
     *
     * <p>This method performs whatever operation this executable represents.
     * Implementations should be idempotent where possible, but this is not
     * strictly required.
     *
     * <p>For conditional execution, see {@link Rule} and {@link Effect}.
     * For executable logic that operates on data, see {@link Action}.
     */
    void execute();
}
