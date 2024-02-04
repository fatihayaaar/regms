FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/gateway-server-1.0.jar gateway-server.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/gateway-server.jar"]