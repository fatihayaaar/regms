FROM amazoncorretto:19

WORKDIR /app
COPY build/libs/gateway-server-1.0.jar gateway-server.jar

VOLUME /tmp

ENV SERVER_PORT=8088
ENV GATEWAY_SERVER_PORT=8088

ENV KEYCLOAK_CLIENT_ID="regms-client"
ENV KEYCLOAK_CLIENT_SECRET="xUGMo8uvkWL9l4JFmyiKaUDwwsIGni6m"
ENV OAUTH2_RESOURCE_SERVER_JWT_ISSUER_URI="http://localhost:8081/auth/realms/regms"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/gateway-server.jar"]