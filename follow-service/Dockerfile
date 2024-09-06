FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/follow-service-1.0.jar follow-service.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=0
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"

ENV NEO4J_HOST=localhost
ENV NEO4J_PORT=7687
ENV NEO4J_USERNAME=neo4j
ENV NEO4J_PASSWORD=12345678

ENV ZIPKIN_SERVER_URL="http://localhost:9411"
ENV OAUTH2_RESOURCE_SERVER_JWT_ISSUER_URI="http://localhost:8081/auth/realms/regms"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/follow-service.jar"]