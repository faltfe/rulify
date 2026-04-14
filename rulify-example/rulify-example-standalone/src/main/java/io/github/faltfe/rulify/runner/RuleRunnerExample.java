package io.github.faltfe.rulify.runner;

import io.github.faltfe.rulify.runner.api.RulifyRunner;

public class RuleRunnerExample {

    public static void main(String[] args) {
        RulifyRunner runner = new RuleRunner("io.github.faltfe.rulify");
        runner.run();
    }
}
