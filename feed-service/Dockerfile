FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/feed-service-1.0.jar feed-service.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=0
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/feed-service.jar"]