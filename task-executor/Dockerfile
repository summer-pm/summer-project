FROM gradle:7.6.2-jdk17-focal AS build


COPY task-executor/src /app/task-executor/src
COPY task-executor/build.gradle.kts /app/task-executor/build.gradle.kts
COPY task-shared-domain/src  /app/task-shared-domain/src
COPY task-shared-domain/build.gradle.kts /app/task-shared-domain/build.gradle.kts
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts
COPY  task-executor/Driver.java /app/task-executor/Driver.java
COPY  task-executor/Driver.py /app/task-executor/Driver.py

WORKDIR /app

RUN gradle task-executor:build --no-daemon
RUN find . | sed -e "s/[^-][^\/]*\// |/g" -e "s/|\([^ ]\)/|-\1/"

FROM openjdk:17-jdk

RUN  microdnf  install -y python3
RUN  microdnf install gcc python3-devel
RUN python3 -m pip install psutil


COPY --from=build /app/task-executor/build/libs/task-executor-1.0-SNAPSHOT.jar /app/task-executor/your-app.jar
COPY --from=build /app/task-executor/Driver.java /app/task-executor/Driver.java
COPY --from=build /app/task-executor/Driver.py /app/task-executor/Driver.py

WORKDIR /app/task-executor

CMD ["java", "-jar", "your-app.jar"]