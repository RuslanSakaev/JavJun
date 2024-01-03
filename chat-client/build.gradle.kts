plugins {
    id("java")
}

group = "org.sakaevrs.chat-client"
version = "1.0-SNAPSHOT"

sourceCompatibility = "21"
targetCompatibility = "21"

repositories {
    mavenCentral()
}

dependencies {
    // Зависимости, специфичные для chat-client
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    implementation ("org.slf4j:slf4j-api:1.7.32")
    implementation ("ch.qos.logback:logback-classic:1.4.12")

    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.1")
}

compileJava.options.encoding = "UTF-8"
