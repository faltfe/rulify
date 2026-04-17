---
title: Runner
---

# Runner

The Rulify runner is an extension built on top of the core `rulify-api` library.
It provides a runtime that discovers and executes rules or effects in a target environment.

## Available runner implementations

- `rulify-runner-standalone` for Java SE applications
- `rulify-runner-jakarta` for Jakarta CDI environments
- `rulify-spring-boot3-starter` for Spring Boot 3 applications

## How the runner works

1. Create a custom `Rule` or `Effect`.
2. Annotate the rule or effect class if required by the runner implementation.
3. Create or inject a `RulifyRunner` instance.
4. Call `runner.run()`.

### Java standalone example

```java
public class RuleScanner {

    public static void main(String[] args) {
        RulifyRunner runner = new RuleRunner("io.github.faltfe.rulify");
        runner.run();
    }
}
```

### Jakarta CDI example

```java
@Singleton
public class RuleScanner {

    @Inject
    @RulifyConfig(path = "io.github.faltfe.rulify")
    private RulifyRunner runner;

    @PostConstruct
    public void init() {
        runner.run();
    }
}
```

## Best practices

- Keep your rules small and focused on a single business decision.
- Reuse shared conditions and actions across multiple rules.
- Prefer `Effect` when a rule must modify data or trigger side effects.
- Use a single package namespace for scanning rules to simplify runner configuration.
