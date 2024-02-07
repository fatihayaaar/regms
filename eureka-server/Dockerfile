FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/eureka-server-1.0.jar eureka-server.jar

VOLUME /tmp

ENV SERVER_PORT=8761
ENV PROFILE=native
ENV CONFIG_SERVER_URL="http://localhost:8888"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/eureka-server.jar"]