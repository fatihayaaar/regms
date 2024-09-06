FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/gateway-server-1.0.jar gateway-server.jar

ENV PROFILES=native,docker
ENV SERVER_PORT=8088
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENV KEYCLOAK_CLIENT_ID="regms-client"
ENV KEYCLOAK_CLIENT_SECRET="xUGMo8uvkWL9l4JFmyiKaUDwwsIGni6m"
ENV OAUTH2_RESOURCE_SERVER_JWT_ISSUER_URI="http://localhost:8081/auth/realms/regms"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/gateway-server.jar"]