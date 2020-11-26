# spring-boot-template

## Quick start

To create a new spring boot project, you can directly use `dy-spring-boot-samples` as a project or add `dy-spring-boot-starter` to your own spring boot project like this:

dy-spring-boot-samples/pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.4.0</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>pom</packaging>

    <groupId>club.differentialmanifold</groupId>
    <artifactId>dy-spring-boot-samples</artifactId>
    <version>1.0-SNAPSHOT</version>

    <modules>
        <module>dy-spring-boot-samples-core</module>
    </modules>

    <properties>
        <maven.deploy.skip>true</maven.deploy.skip>
    </properties>

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

</project>
```

dy-spring-boot-samples/dy-spring-boot-samples-core/pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <groupId>club.differentialmanifold</groupId>
        <artifactId>dy-spring-boot-samples</artifactId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>dy-spring-boot-samples-core</artifactId>

    <dependencies>
        <dependency>
            <groupId>club.differentialmanifold</groupId>
            <artifactId>dy-core-spring-boot-starter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- test dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```