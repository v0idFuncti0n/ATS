FROM eclipse-temurin:17-jdk-focal

ADD build/libs/ats-configuration-service-server-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]