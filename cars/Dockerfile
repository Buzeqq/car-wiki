FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} cars.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "/cars.jar"]