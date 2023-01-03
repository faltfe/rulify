package de.faltfe.rulify.runner.api;

/**
 * This interfaces is intended to be the entrypoint for each implemented runner.
 */
@FunctionalInterface
public interface RulifyRunner {
    void run();
}
