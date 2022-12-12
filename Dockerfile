FROM openjdk:11

RUN apt update -y && \
    apt upgrade -y

WORKDIR /cars
COPY ./cars/target/cars-0.0.1-SNAPSHOT.jar ./
RUN java -jar cars-0.0.1-SNAPSHOT.jar &

WORKDIR /producers
COPY ./producers/target/producers-0.0.1-SNAPSHOT.jar ./
RUN java -jar producers-0.0.1-SNAPSHOT.jar &

WORKDIR /gateway
COPY ./gateway/target/gateway-0.0.1-SNAPSHOT.jar ./
CMD ["java", "-jar", "gateway-0.0.1-SNAPSHOT.jar"]
