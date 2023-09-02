# spring-boot-template

## Quick start

To create a new spring boot project, you can directly use `dy-spring-boot-samples` as a project or add `dy-spring-boot-starter` to your own spring boot project like this:

```xml
<project>
...

    <repositories>
        <repository>
            <id>github-dy-spring-boot</id>
            <name>dy spring boot</name>
            <url>https://maven.pkg.github.com/differentialmanifold/dy-spring-boot</url>
        </repository>
    </repositories>

    <dependencies>
        <dependency>
            <groupId>club.differentialmanifold</groupId>
            <artifactId>dy-core-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
    </dependencies>

...
</project>
```
