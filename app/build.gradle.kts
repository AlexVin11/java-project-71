import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    application
    checkstyle
    java
    jacoco
    id("io.freefair.lombok") version "8.6"
    id("se.patrikerdes.use-latest-versions") version "0.2.18" //don't understand do I need this
    id("com.github.ben-manes.versions") version "0.41.0" //don't understand do I need this
    id("com.github.johnrengelman.shadow") version "8.1.1" //don't understand do I need this
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation ("info.picocli:picocli:4.7.5")
    // https://mvnrepository.com/artifact/info.picocli/picocli-codegen
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.0")
}

tasks.withType<JavaCompile>(){
    options.compilerArgs.addAll(listOf("-Aproject=${project.group}/${project.name}"))
}

tasks.getByName("run", JavaExec::class) {
    standardInput = System.`in`
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

tasks.test {
    useJUnitPlatform()
    // https://technology.lastminute.com/junit5-kotlin-and-gradle-dsl/
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}

tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}
