FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app
COPY target/bank-adapter-mockbank-b-1.0.0.jar app.jar
EXPOSE 8092
ENTRYPOINT ["java", "-jar", "app.jar"]
