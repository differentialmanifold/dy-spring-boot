# spring-boot-template

## Quick start

To create a new spring boot project, you can directly use `dy-spring-boot-samples` as a project or add `dy-spring-boot-starter` to your own spring boot project like this:

```xml
<project>
...

    <repositories>
        <repository>
            <id>dy-public</id>
            <name>dy public</name>
            <url>https://nexus3.differentialmanifold.club/repository/maven-public/</url>
        </repository>
        <repository>
            <id>alimaven</id>
            <name>aliyun maven</name>
            <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
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
