FROM openjdk:11-jre-slim
EXPOSE 8089
ARG JAR_FILE=target/appProducts.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.agd=file:/dev/./urandom","-jar", "/app.jar"]
