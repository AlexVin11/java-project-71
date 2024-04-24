plugins {
    application
    //checkstyle
    java
    //jacoco
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("com.github.ben-manes.versions") version "0.41.0"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    /*testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")*/
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.14.0")
    implementation ("info.picocli:picocli:4.7.5")
    // https://mvnrepository.com/artifact/info.picocli/picocli-codegen
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")
}

tasks.withType<JavaCompile>(){
    options.compilerArgs.addAll(listOf("-Aproject=${project.group}/${project.name}"))
}

application {
    mainClass.set("hexlet.code.App")
}

tasks.test {
    useJUnitPlatform()
}

/*tasks.jacocoTestReport {
    reports { xml.required.set(true) }
}*/
