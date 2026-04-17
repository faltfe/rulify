---
title: Installation
---

# Installation

Rulify is distributed as Maven artifacts and can be added to any Java project with Maven.

## Core dependency

Add the `rulify-api` dependency to your project:

```xml
<dependency>
  <groupId>io.github.faltfe</groupId>
  <artifactId>rulify-api</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Runner dependencies

Rulify offers several runner implementations for different platforms. Choose the dependency that matches your target environment.

### Java SE standalone

```xml
<dependency>
  <groupId>io.github.faltfe</groupId>
  <artifactId>rulify-runner-standalone</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Jakarta CDI

```xml
<dependency>
  <groupId>io.github.faltfe</groupId>
  <artifactId>rulify-runner-jakarta</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Spring Boot

```xml
<dependency>
  <groupId>io.github.faltfe</groupId>
  <artifactId>rulify-spring-boot3-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

## Recommended Java versions

Rulify requires Java 17 or higher. The repository includes a CI matrix for Java 17, 21, and 25.

## Build the documentation locally

To build and preview the docs locally, use the `jekyll` Docker image or a local Ruby/Jekyll installation.

```bash
docker run --rm -v $(pwd -W):/srv/jekyll jekyll/jekyll bundle install

docker run --rm -v $(pwd -W):/srv/jekyll --publish '[::1]:4000:4000' jekyll/jekyll jekyll serve
```
