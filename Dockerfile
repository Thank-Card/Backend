FROM openjdk:17-alpine

ARG JAR_FILE=build/libs/*SNAPSHOT.jar

COPY ${JAR_FILE} thanks_card.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/thanks_card.jar"]