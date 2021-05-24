package de.faltfe.rulify.runner;

public class RuleRunnerExample {

    public static void main(String[] args) {
        AnnotationRunner runner = new RuleRunner("de.faltfe.rulify");
        runner.run();
    }
}
