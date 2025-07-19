FROM openjdk:17-jdk-slim as builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests


FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=mySuperCoolBuilderStage /app/target/txbank-0.0.1-SNAPSHOT.jar /app/app.jar # І тут ми посилаємося на цю ж назву
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]