package io.github.faltfe.rulify.runner.api;

/**
 * This interface is intended to be the entry point for each implemented runner.
 */
@FunctionalInterface
public interface RulifyRunner {

    /**
     * Executes the runner to process and apply all configured rules.
     */
    void run();
}
