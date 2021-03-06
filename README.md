[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) [![codecov](https://codecov.io/gh/faltfe/rulify/branch/develop/graph/badge.svg?token=8KSNJFNNFU)](https://codecov.io/gh/faltfe/rulify)

[![Java 8 - Build, run tests and create coverage](https://github.com/faltfe/rulify/actions/workflows/maven-codecov-java8.yml/badge.svg)](https://github.com/faltfe/rulify/actions/workflows/maven-codecov-java8.yml) [![Java 11 - Build, run tests and create coverage](https://github.com/faltfe/rulify/actions/workflows/maven-codecov-java11.yml/badge.svg)](https://github.com/faltfe/rulify/actions/workflows/maven-codecov-java11.yml) 

# rulify

## What is rulify? 🤔

Some time ago I had the challenge of mapping a defined set of rules. That was
the birth of _rulify_.

_rulify_ itself is a library that provides a framework for the implementation of
rules. A rule is an abstract representation of exactly one condition, which is
followed by an action. In other words, a rule describes an if-then structure.

The special feature of _rulify_ is that both conditions and actions can be
created independently of the rule, so that they can be reused several times in
different rules.

The library does not claim to fulfil the criteria of a rule engine, nor to
replace it.

## How _rulify_ works? 🔨

_rulify_ offers two classes which can be used as a start for the implementation
of a rule.

![rulify-api-uml](./asserts/rulify-api-uml.png)

1. A `Rule` will evaluate a `Condition` and perform the provided `Action`
2. An `Effect` will evaluate a `Condition`, modify the data with the given
   `Modifier` and run lastly the provided `Action`.

A `Rule` or `Effect` always works with data provided by your own implementation.
This data must be managed by a single object, which is ideally a POJO.

## How to get started with _rulify_? 🎆

All you need is a POJO and a custom implementation of a `Rule` or a `Effect`
and implement the mandatory methods. Let's say there is our POJO `Cat` that
looks like

```java
public class Cat {

    private String name = "Meow 🐱";
    private boolean cute;

    public Cat(boolean isCute) { this.cute = isCute; }

    public void setName(String name) { this.name = name; }

    public boolean isCute() { return cute; }

    public void purr() { System.out.println("🐱 + ", name); }
}
```

Now we need your `Rule` which will run on the `Cat`.

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

The last part is to execute the `Rule`.

```java
Executable rule=new CatRule();
rule.execute();
```

![rulify-rule-effect-flow](./asserts/rulify-rule-effect-flow.drawio.png)

## What are the benefits of _rulify_?

The first and main advantage of this library compared to what actually? In my
opinion, there two groups to which _rulify_ can be compared.

### Benefits compared to plain If-Then implementations

At first glance, using rulify seems significantly more complicated than an
If-Else-Then construct. For simple conditions it is true, but as soon as things
get more complex, the strengths of rulify come to the fore.

1. A rule is reusable.
2. There is a separation between the components condition and execution.
3. The individual components are easier to test.~~~~

### Benefits compared to any rule engine

As mentioned before a rule engine is much more complex than this library
because _rulify_ aims another target. The main target is to provide a better
mechanism for If-Then conditions.

---

# Rulify runner 🏃‍♂️🏃‍♀️

A second library provides mechanism to scan a project for rules and execute the
connected `Rule` or `Effect`. At the moment

- Java SE
- Java EE 8
- Jakarta (Java 11)

are supported. Spring Boot is not supported yet, because it uses an own
annotation scanner.

## How to start with rulify runner?

_rulify runner_ provides an API with the interface `RulifyRunner` that has the
method `run()`. Each implementation for a specific platform should implement
this method.

The first step is to annotate any class with `@Rule(MyRule.class)` and provide a
marker interface that extends `Executable`. The next step is to configure the
runner. The `rulify runner` needs to know which packages should be scanned. Each
runner instance can scan exactly one package recursively.

![rulify-runner](./asserts/rulify-runner-uml.drawio.png)

For Java SE projects this will look like

```java
RulifyRunner runner=new RuleRunner("de.faltfe.rulify");
runner.run();
```

and for Java projects that uses dependency injection

```java
@Inject
@RulifyConfig(path = "de.faltde.rulify")
private RulifyRunner runner;

public void run(){
    runner.run();
}
```
