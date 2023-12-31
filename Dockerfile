FROM openjdk:20

COPY out/artifacts/JavJun_jar/JavJun.jar /tmp/JavJun.jar
WORKDIR /tmp
CMD ["java", "-jar", "/tmp/JavJun.jar"]