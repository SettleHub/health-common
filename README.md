# SettleHub Health Commons

A library for unified health checking of SettleHub microservices. Adds standardized `/health` and `/health/heartbeat` endpoints.

## Installation

Add the GitHub Packages repository and dependency to your microservice's `pom.xml` file.

### 1. Repository Configuration

```xml
<repositories>
    <repository>
        <id>github</id>
        <name>GitHub SettleHub Apache Maven Packages</name>
        <url>https://maven.pkg.github.com/SettleHub/health-common</url>
    </repository>
</repositories>
```

### 2. Add Dependency

```xml
<dependency>
    <groupId>org.settlehub.commons</groupId>
    <artifactId>health</artifactId>
    <version>0.0.1-DEV</version>
</dependency>
```

## Usage

To enable the endpoints, add the @EnableHealthCheck annotation to your application's main class:

```java
import org.settlehub.health.annotation.EnableHealthCheck;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHealthCheck
@SpringBootApplication
public class MicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceApplication.class, args);
    }
}
```

> [!NOTE]
> The library uses the `spring.application.name` property from your `application.yml` or `application.properties` to identify the service in responses.
