FROM adoptopenjdk/openjdk11:latest as build
WORKDIR /app

COPY mvnw .
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/api-login-latest && (cd target/api-login-latest; jar -xf ../*.jar)
FROM adoptopenjdk/openjdk11:latest
COPY target/api-login-latest.jarr /app
CMD ["java", "-jar", "api-login-latest.jar"]