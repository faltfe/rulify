package io.github.faltfe.rulify.rules;

import io.github.faltfe.rulify.api.Executable;

public class CustomRuleExecution {
    public static void main(String[] args) {
        Executable rule = new CustomRule();
        rule.execute();
    }
}
