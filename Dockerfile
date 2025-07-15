
FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer="jonatham01@hotmail.com"
LABEL version="1.0"
LABEL description="Backend Application of Hotels"

WORKDIR /app
COPY target/hotel-manager-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090
CMD ["java", "-jar", "app.jar"]