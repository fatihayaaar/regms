FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/eureka-server-1.0.jar eureka-server.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=8761
ENV SERVER_HOST="localhost"
ENV SERVER_URL="http://localhost:8761"
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/eureka-server.jar"]