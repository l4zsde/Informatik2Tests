val junitVersion = "5.8.2"

plugins {
    java
}

group = "de.l4zs"
version = "0.8"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter-api", junitVersion)
    testImplementation("org.junit.jupiter", "junit-jupiter-params", junitVersion)
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine", junitVersion)
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
