FROM gradle:7.6.2-jdk17-focal AS build


COPY task-microservice/src /app/task-microservice/src
COPY task-microservice/build.gradle.kts /app/task-microservice/build.gradle.kts
COPY task-shared-domain/src  /app/task-shared-domain/src
COPY task-shared-domain/build.gradle.kts /app/task-shared-domain/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app

CMD ["gradle", "task-microservice:bootRun"]


