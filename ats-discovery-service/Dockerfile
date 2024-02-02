FROM eclipse-temurin:17-jdk-focal

ADD build/libs/ats-discovery-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8761

ENTRYPOINT ["java","-jar","app.jar"]