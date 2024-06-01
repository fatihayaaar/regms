FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/profile-service-1.0.jar profile-service.jar

VOLUME /tmp

ENV SERVER_PORT=8086
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/profile-service.jar"]