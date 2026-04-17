---
title: Getting Started
---

# Getting Started

Rulify lets you implement business rules in a reusable, modular way.

## Create a rule

A `Rule` evaluates a `Condition` and performs an `Action` when the condition is true.

```java
public class CatRule extends Rule<Cat> {

    @Override
    public Cat data() {
        return new Cat(true);
    }

    @Override
    public Condition<Cat> condition() {
        return Cat::isCute;
    }

    @Override
    public Action<Cat> action() {
        return Cat::purr;
    }
}
```

## Execute the rule

Use any class implementing `Executable` to run the rule.

```java
public class MyRuleExecutor {

    public static void main(String[] args) {
        Executable rule = new CatRule();
        rule.execute();
    }
}
```

## Create an effect

If you need to mutate data before or after the action, use `Effect` instead of `Rule`.

- A `Rule` applies a condition and executes an action.
- An `Effect` applies a condition, modifies data, and executes an action.

This makes `Effect` useful for workflows that need side effects or data transformation.
