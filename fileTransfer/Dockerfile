FROM openjdk:11

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fileTransfer.jar

ARG STORAGE_LOCATION=files
RUN mkdir ${STORAGE_LOCATION}

EXPOSE 8083
ENTRYPOINT ["java", "-jar", "/fileTransfer.jar"]