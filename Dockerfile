FROM eclipse-temurin:21-jre
COPY "target/futbolclub-1.0.0.jar.original" "app.jar"
EXPOSE 8218
ENTRYPOINT ["java", "-jar", "app.jar"]
