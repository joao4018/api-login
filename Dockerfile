FROM adoptopenjdk/openjdk11:latest
WORKDIR /workspace/app

COPY mvnw .
COPY pom.xml .
COPY .mvn .mvn
COPY src src

RUN ./mvnw install -DskipTests
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
FROM adoptopenjdk/openjdk11:latest
WORKDIR /app
ARG DEPENDENCY=/workspace/app/target
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${DEPENDENCY}/api-login-latest.jar /app/api-login.jar
CMD ["java", "-jar", "api-login-latest.jar"]
