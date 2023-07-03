FROM maven:3.8.3-openjdk-17 AS build



COPY Driver.py /app/Driver.py
COPY Driver.java /app/Driver.java
COPY src /app/src
COPY pom.xml /app/pom.xml

WORKDIR /app

RUN mvn package


FROM maven:3.8.3-openjdk-17


RUN  microdnf  install -y python3
RUN  microdnf install gcc python3-devel
RUN python3 -m pip install psutil

COPY --from=build /app/target/Task-executor-1.0-SNAPSHOT.jar /app/your-app.jar


COPY --from=build /app/Driver.py /app/Driver.py
COPY --from=build /app/Driver.java /app/Driver.java
WORKDIR /app
# Запускаем JAR-файл при старте контейнера
CMD ["java", "-jar", "your-app.jar"]