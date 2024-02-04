FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/admin-server-1.0.jar admin-server.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/admin-server.jar"]