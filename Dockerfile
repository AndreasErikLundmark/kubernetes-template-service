FROM maven:3.8.7-openjdk-18-slim AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:18-slim

WORKDIR /app

COPY --from=build /app/target/kubernetes.setup-0.0.1-SNAPSHOT.jar /app/kubernetes.setup.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/kubernetes.setup.jar"]