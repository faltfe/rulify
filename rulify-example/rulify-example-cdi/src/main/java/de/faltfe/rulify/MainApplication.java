package de.faltfe.rulify;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class MainApplication {
    public static void main(String[] args) {
        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {
            StartupRuleRunner runner = container.select(StartupRuleRunner.class).get();
            runner.sayHello();
        }
    }
}
