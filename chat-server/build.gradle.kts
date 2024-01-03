plugins {
    id("java")
}

group = "org.sakaevrs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // Зависимости, специфичные для chat-server
    implementation ("com.mysql:mysql-connector-j:8.2.0")
    implementation ("org.hibernate:hibernate-java8:6.0.0.Alpha7")
    implementation ("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> { options.encoding = "UTF-8" }