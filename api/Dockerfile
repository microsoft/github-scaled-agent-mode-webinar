FROM maven:3.9-eclipse-temurin-21-alpine as builder

WORKDIR /app
COPY . .

# Build using Maven from the official image
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "app.jar"]