FROM gradle:7.6.2-jdk17-focal


COPY task-microservice/src /app/task-microservice/src
COPY task-microservice/build.gradle.kts /app/task-microservice/build.gradle.kts
COPY task-shared-domain/src  /app/task-shared-domain/src
COPY task-shared-domain/build.gradle.kts /app/task-shared-domain/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app
RUN gradle task-microservice:build
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"
COPY  /app/task-microservice/build/libs/task-microservice-1.0-SNAPSHOT.jar /app/task-microservice/your-app.jar

WORKDIR /app/task-microservice

CMD ["java", "-jar", "your-app.jar"]


