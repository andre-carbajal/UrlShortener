FROM maven:3.9.7-eclipse-temurin-21

WORKDIR /app

COPY pom.xml /app/pom.xml
COPY src /app/src

RUN mvn clean package -DskipTests

RUN cp target/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]