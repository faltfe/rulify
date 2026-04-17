---
title: Concepts
---

# Concepts

Rulify is built around a small set of reusable concepts.

## Rule

A `Rule` is an abstract representation of a single business decision.
It evaluates a `Condition` and executes an `Action` when the condition is met.

## Effect

An `Effect` is similar to a `Rule`, but it also applies a `Modifier` to the data.
Use an `Effect` when the business logic needs to change state or apply side effects.

## Condition

A `Condition` is a reusable predicate that evaluates the current data object.
It represents the `if` portion of a business rule.

## Action

An `Action` is the reusable function that runs when the condition is true.
It represents the `then` portion of a business rule.

## Modifier

A `Modifier` is used by `Effect` to change or enrich the current data before or after the action.
This gives Rulify more flexibility for stateful flows.

## Data object

Rules and effects work on a single data object provided by the user.
This is typically a POJO that contains all values needed for condition evaluation and actions.

## Reuse and composition

The main advantage of Rulify is that conditions, modifiers, and actions can be defined independently and reused across multiple rules.
This helps keep business logic clean, testable, and easy to maintain.

## Rule vs Effect flow

- `Rule`: condition → action
- `Effect`: condition → modifier → action

![Rule and Effect flow](/assets/rulify-rule-effect-flow.drawio.png)
