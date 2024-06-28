FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/follow-service-1.0.jar follow-service.jar

VOLUME /tmp

ENV SERVER_PORT=8084
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/follow-service.jar"]