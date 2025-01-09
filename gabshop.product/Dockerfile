FROM openjdk:21-jdk-slim

WORKDIR /app

COPY target/gabshop.product-1.0.0.jar gabshop.product-1.0.0.jar

ENTRYPOINT ["java", "-jar", "gabshop.product-1.0.0.jar", "--spring.profiles.active=docker"]