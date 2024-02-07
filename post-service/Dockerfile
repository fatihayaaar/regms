FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/post-service-1.0.jar post-service.jar

VOLUME /tmp

ENV SERVER_PORT=8085
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/post-service.jar"]