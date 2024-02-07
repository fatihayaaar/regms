FROM amazoncorretto:19.0.2-alpine3.17

WORKDIR /app
COPY build/libs/user-service-1.0.jar user-service.jar

VOLUME /tmp

ENV SERVER_PORT=8087
ENV CONFIG_SERVER_URL="http://localhost:8888"
ENV EUREKA_SERVER_HOST="localhost"
ENV EUREKA_SERVER_URL="http://localhost:8761"

ENV LDAP_SERVER_URL="ldap://localhost:389"
ENV LDAP_BASE="dc=fayardev,dc=com"
ENV LDAP_USERNAME="cn=admin"
ENV LDAP_PASSWORD="admin"

ENV OAUTH2_RESOURCE_SERVER_JWT_ISSUER_URI="http://localhost:8081/auth/realms/regms"
ENV SPRING_BOOT_ADMIN_SERVER_URL="http://localhost:8077"
ENV ZIPKIN_SERVER_URL="http://localhost:9411"

ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-jar", "/app/user-service.jar"]