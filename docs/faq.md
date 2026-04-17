---
title: FAQ
---

# FAQ

## What is Rulify?

Rulify is a lightweight Java framework for modeling business rules as reusable code.
It focuses on clear `if-then` logic with separate condition and action components.

## Is Rulify a rule engine?

No. Rulify is intentionally simpler than a full rule engine.
It is designed to provide a clean way to express business rules without the complexity of a complete engine.

## When should I use `Rule` vs `Effect`?

- Use `Rule` when you need to evaluate a condition and execute an action.
- Use `Effect` when the logic also needs to modify the data or apply a transformation before the action.

## Can I use Rulify with Spring Boot?

Yes. The project includes `rulify-spring-boot3-starter` for Spring Boot 3 integration.
Use the starter dependency and configure rule scanning through the Spring Boot runtime.

## How do I run the examples?

Open the example module folder and run Maven, for example:

```bash
mvn -pl rulify-example-standalone test
```

## Where can I find the repository?

The source code is available on GitHub:

https://github.com/faltfe/rulify
