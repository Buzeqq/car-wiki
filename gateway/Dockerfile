FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} gateway.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/gateway.jar"]