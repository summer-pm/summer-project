FROM gradle:7.6.2-jdk17-focal AS build


COPY eureka-server/src /app/eureka-server/src
COPY eureka-server/build.gradle.kts /app/eureka-server/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app
RUN gradle eureka-server:build
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

FROM openjdk:17-jdk

COPY --from=build  /app/eureka-server/build/libs/eureka-server-0.0.1-SNAPSHOT.jar /app/eureka-server/your-app.jar

WORKDIR /app/eureka-server

CMD ["java", "-jar", "your-app.jar"]


