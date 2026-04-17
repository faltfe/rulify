---
title: Examples
---

# Examples

The repository includes example modules that demonstrate how to use Rulify in different runtime environments.

## Example modules

- `rulify-example-jakarta` — Jakarta CDI example
- `rulify-example-spring-boot` — Spring Boot 3 example
- `rulify-example-standalone` — Java standalone example

## Recommended exploration path

1. Start with `rulify-example-standalone` to understand the basic rule and effect execution flow.
2. Review `rulify-example-jakarta` to see how Rulify integrates with CDI and dependency injection.
3. Review `rulify-example-spring-boot` for Spring Boot starter setup and runtime execution.

## What you will learn

- How to create rule classes and configure rule scanning
- How to wire Rulify into a Java application runtime
- How to execute and test business rules independently of the application framework

## Running examples

Each example module includes its own `pom.xml` and can be executed with Maven.
Use the module directory and run:

```bash
mvn -pl <module-name> test
```

Replace `<module-name>` with one of the example folders, for example `rulify-example-standalone`.
