plugins {
    java
}

group = "ie.ronanodea"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.1") // API for writing tests
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.1") // Runtime engine
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.1") // For @ParameterizedTest

    // For running test suites (BankingAppTestRunner.java)
    testImplementation("org.junit.platform:junit-platform-suite-api:1.10.1")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine:1.10.1")
}

tasks.test {
    useJUnitPlatform()
}