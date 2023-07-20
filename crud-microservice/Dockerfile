FROM gradle:7.6.2-jdk17-focal AS build


COPY crud-microservice/src /app/crud-microservice/src
COPY crud-microservice/build.gradle.kts /app/crud-microservice/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app
RUN gradle crud-microservice:build
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

FROM openjdk:17-jdk

COPY --from=build  /app/crud-microservice/build/libs/crud-microservice-0.0.1-SNAPSHOT.jar /app/crud-microservice/your-app.jar

WORKDIR /app/crud-microservice

CMD ["java", "-jar", "your-app.jar"]


