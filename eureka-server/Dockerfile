FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/eureka-server-1.0.jar eureka-server.jar

VOLUME /tmp

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/eureka-server.jar"]