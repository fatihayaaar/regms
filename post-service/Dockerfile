FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/post-service-1.0.jar post-service.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=0
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"
ENV CASSANDRA_CONTACT_POINTS=127.0.0.1

ENV OAUTH2_RESOURCE_SERVER_JWT_ISSUER_URI="http://localhost:8081/auth/realms/regms"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/post-service.jar"]