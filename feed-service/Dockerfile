FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/feed-service-1.0.jar feed-service.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/feed-service.jar"]