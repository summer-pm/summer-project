FROM openjdk:17-oracle
VOLUME /tmp
ADD build/libs/*.jar dockerimage.jar
EXPOSE 8080
RUN bash -c 'touch /dockerimage.jar '
ENTRYPOINT ["java","-jar", "dockerimage.jar"]