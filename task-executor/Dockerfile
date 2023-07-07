FROM gradle:7.6.2-jdk17-focal AS build


COPY Driver.py /app/Driver.py
COPY Driver.java /app/Driver.java
COPY src /app/src
COPY build.gradle.kts /app/build.gradle.kts
COPY settings.gradle.kts /app/settings.gradle.kts

WORKDIR /app

RUN gradle build --no-daemon

FROM openjdk:17-jdk

RUN  microdnf  install -y python3
RUN  microdnf install gcc python3-devel
RUN python3 -m pip install psutil

COPY --from=build /app/build/libs/task-executor-1.0-SNAPSHOT.jar /app/your-app.jar


COPY --from=build /app/Driver.py /app/Driver.py
COPY --from=build /app/Driver.java /app/Driver.java
WORKDIR /app

CMD ["java", "-jar", "your-app.jar"]