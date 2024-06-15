plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"

    id("io.spring.dependency-management") version "1.1.4"
}

group = "excellence"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



dependencies {

    implementation("org.springframework:spring-core")
    //implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    /*implementation("org.springframework.security:spring-security-web")
    implementation("org.springframework:spring-context-support:6.0.6")
    implementation("org.springframework.security:spring-security-core")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.security:spring-security-config")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")*/

    implementation("com.github.ben-manes.caffeine:caffeine")

    //jakarta validation
    implementation ("jakarta.validation:jakarta.validation-api:3.0.2")
    implementation ("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation ("org.hibernate:hibernate-validator-annotation-processor:8.0.1.Final")

    //mail
    implementation("com.sun.mail:jakarta.mail:2.0.1")
    implementation("jakarta.mail:jakarta.mail-api:2.1.3")
    implementation("com.sun.activation:jakarta.activation:2.0.1")


    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.postgresql:postgresql:42.7.3")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("postgresql:postgresql:9.1-901-1.jdbc4")
    implementation("org.hibernate:hibernate-core:6.4.2.Final")
    implementation("org.hibernate:hibernate-hikaricp:6.4.2.Final")


    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.14.2")


    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))

}

tasks.test {
    useJUnitPlatform()
}