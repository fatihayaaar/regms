FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/user-service-1.0.jar user-service.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/user-service.jar"]