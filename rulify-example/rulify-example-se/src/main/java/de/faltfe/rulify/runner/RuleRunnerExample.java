package de.faltfe.rulify.runner;

import de.faltfe.rulify.api.RulifyRunner;

public class RuleRunnerExample {

    public static void main(String[] args) {
        RulifyRunner runner = new RuleRunner("de.faltfe.rulify");
        runner.run();
    }
}
