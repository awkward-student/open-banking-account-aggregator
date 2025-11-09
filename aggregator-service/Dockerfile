FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY target/aggregator-service-1.0.0.jar app.jar

ENTRYPOINT ["sh","-c","exec java -Dspring.kafka.bootstrap-servers=${SPRING_KAFKA_BOOTSTRAP_SERVERS:-kafka:29092} -jar app.jar"]
