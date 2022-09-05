FROM adoptopenjdk/openjdk11:latest
WORKDIR /app
COPY target/api-login-latest.jar /app
CMD ["java", "-jar", "api-login-latest.jar"]
