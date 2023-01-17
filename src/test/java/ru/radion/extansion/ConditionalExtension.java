package ru.radion.extansion;


import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtensionContext;

public class ConditionalExtension implements ExecutionCondition {
    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        System.out.println();
        return !System.getProperty("user.name").equals("ASRock")
                ? ConditionEvaluationResult.disabled("skipped test")
                : ConditionEvaluationResult.enabled("enabled default");
    }
}
