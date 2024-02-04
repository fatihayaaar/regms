FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/config-server-1.0.jar config-server.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/config-server.jar"]