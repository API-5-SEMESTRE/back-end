FROM openjdk:11-jre-slim

EXPOSE 8080

COPY ./target/sas-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app

ENTRYPOINT ["java", "-jar", "sas-0.0.1-SNAPSHOT.jar"]