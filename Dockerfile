FROM maven:3.6.3-openjdk-11-slim AS MAVEN_BUILD
COPY ./ ./
#TODO: jlink for build
RUN mvn clean package

#TODO: -> alpine to further shrink image
FROM openjdk:11-jre-slim
COPY --from=MAVEN_BUILD /target/stones-homework-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]
