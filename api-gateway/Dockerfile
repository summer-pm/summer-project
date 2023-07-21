FROM gradle:7.6.2-jdk17-focal AS build


COPY api-gateway/src /app/api-gateway/src
COPY api-gateway/build.gradle.kts /app/api-gateway/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app
RUN gradle api-gateway:build
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

FROM openjdk:17-jdk

COPY --from=build  /app/api-gateway/build/libs/api-gateway-0.0.1-SNAPSHOT.jar /app/api-gateway/your-app.jar

WORKDIR /app/api-gateway

CMD ["java", "-jar", "your-app.jar"]


