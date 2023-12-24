plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/junit/junit
    testImplementation("junit:junit:4.13.2")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.dataformat/jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.16.0")
    // https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api
    implementation("javax.xml.bind:jaxb-api:2.3.1")
    implementation("org.glassfish.jaxb:jaxb-runtime:2.3.1")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.4.12")
    // https://mvnrepository.com/artifact/com.mysql/mysql-connector-j
    implementation("com.mysql:mysql-connector-j:8.2.0")
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-java8
    implementation("org.hibernate:hibernate-java8:6.0.0.Alpha7")

}

tasks.test {
    useJUnitPlatform()
}