FROM openjdk:21

COPY "target/futbolclub-1.0.0.jar.original" "app.jar"
EXPOSE 8218
ENTRYPOINT ["java", "-jar", "app.jar"]