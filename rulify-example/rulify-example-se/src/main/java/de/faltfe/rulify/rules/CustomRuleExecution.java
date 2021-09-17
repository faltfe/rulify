package de.faltfe.rulify.rules;

import de.faltfe.rulify.api.Executable;

public class CustomRuleExecution {
    public static void main(String[] args) {
        Executable rule = new CustomRule();
        rule.execute();
    }
}
