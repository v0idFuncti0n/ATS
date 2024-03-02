FROM eclipse-temurin:17-jdk-focal

ADD build/libs/ats-gateway-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","app.jar"]