FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY target/bank-adapter-mockbank-a-1.0.0.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
