FROM eclipse-temurin:21-jre
WORKDIR /opt
ENV PORT=8081
EXPOSE 8081
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
