FROM gradle:7.6.2-jdk17-focal AS build


COPY auth-microservice/src /app/auth-microservice/src
COPY auth-microservice/build.gradle.kts /app/auth-microservice/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app
RUN gradle auth-microservice:build
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

FROM openjdk:17-jdk

COPY --from=build  /app/auth-microservice/build/libs/auth-microservice-0.0.1-SNAPSHOT.jar /app/auth-microservice/your-app.jar

WORKDIR /app/auth-microservice

CMD ["java", "-jar", "your-app.jar"]


