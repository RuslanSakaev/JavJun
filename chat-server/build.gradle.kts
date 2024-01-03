plugins {
    id("java")
}

group = "org.sakaevrs.chat-server"
version = "1.0-SNAPSHOT"

sourceCompatibility = "21"
targetCompatibility = "21"

repositories {
    mavenCentral()
}

dependencies {
    // Зависимости, специфичные для chat-server
    implementation ("com.mysql:mysql-connector-j:8.2.0")
    implementation ("org.hibernate:hibernate-java8:6.0.0.Alpha7")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.0")

    testImplementation ("org.junit.jupiter:junit-jupiter:5.9.1")
}

compileJava.options.encoding = "UTF-8"
