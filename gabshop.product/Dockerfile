# base image
FROM openjdk:21-jdk-slim AS base

WORKDIR /app

COPY target/gabshop.product-1.0.0.jar gabshop.product-1.0.0.jar

#test docker with native databse
FROM base AS docker
ENTRYPOINT ["java", "-jar", "gabshop.product-1.0.0.jar", "--spring.profiles.active=docker"]

#test docker with docker database
FROM base AS dockerdb
ENTRYPOINT ["java", "-jar", "gabshop.product-1.0.0.jar", "--spring.profiles.active=dockerdb"]