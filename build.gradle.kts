plugins {
    java
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.sample.rabbitmq"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-jms")
    implementation("jakarta.jms:jakarta.jms-api:3.1.0")
    implementation("commons-lang:commons-lang:2.0")
    implementation("com.rabbitmq.jms:rabbitmq-jms:3.1.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
