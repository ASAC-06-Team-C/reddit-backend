FROM gradle:8.5-jdk17 AS builder
WORKDIR /app
COPY . .
RUN ["gradle", "clean", "build"]

FROM amazoncorretto:17-alpine
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]