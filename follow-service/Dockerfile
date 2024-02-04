FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/follow-service-1.0.jar follow-service.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/follow-service.jar"]