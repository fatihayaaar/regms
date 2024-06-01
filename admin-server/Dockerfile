FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/admin-server-1.0.jar admin-server.jar

VOLUME /tmp

ENV SERVER_PORT=8077
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_URL="http://localhost:8761"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/admin-server.jar"]