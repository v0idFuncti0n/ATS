FROM eclipse-temurin:17-jdk-focal

ADD build/libs/ats-mail-resume-fetcher-0.0.1-SNAPSHOT.jar app.jar

RUN apt-get update -y && apt-get install -y iputils-ping

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]