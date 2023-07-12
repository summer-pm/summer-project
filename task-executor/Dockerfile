FROM gradle:7.6.2-jdk17-focal AS build


COPY task-executor /app/task-executor
COPY task-shared-domain /app/task-shared-domain
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts


WORKDIR /app

RUN gradle task-executor:build

FROM openjdk:17-jdk

RUN  microdnf  install -y python3
RUN  microdnf install gcc python3-devel
RUN python3 -m pip install psutil

COPY --from=build /app/task-executor/build/libs/task-executor.jar /app/task-executor/your-app.jar
COPY --from=build /app/task-executor/Driver.java /app/task-executor/Driver.java
COPY --from=build /app/task-executor/Driver.py /app/task-executor/Driver.py

WORKDIR /app/task-executor

CMD ["java", "-jar", "your-app.jar"]