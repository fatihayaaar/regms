FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/config-server-1.0.jar config-server.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=8888

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/config-server.jar"]