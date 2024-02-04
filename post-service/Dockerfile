FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/post-service-1.0.jar post-service.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/post-service.jar"]