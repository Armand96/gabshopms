FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gabshop.api.gateway-1.0.0.jar gabshop.api.gateway-1.0.0.jar

ENTRYPOINT ["java", "-jar", "gabshop.api.gateway-1.0.0.jar", "--spring.profiles.active=docker"]