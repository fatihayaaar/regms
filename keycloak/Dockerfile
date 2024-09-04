FROM quay.io/keycloak/keycloak:legacy

ENV DB_VENDOR MYSQL
ENV DB_ADDR mysql
ENV DB_DATABASE keycloak
ENV DB_USER ${DB_USER}
ENV DB_PASSWORD ${DB_PASSWORD}
ENV KEYCLOAK_USER ${KEYCLOAK_USER}
ENV KEYCLOAK_PASSWORD ${KEYCLOAK_PASSWORD}

COPY ./realm/keycloak/realm-file.json /opt/jboss/keycloak/standalone/configuration/realm-file.json
COPY ./theme/regms-theme /opt/jboss/keycloak/themes/regms-theme
COPY ./script/entrypoint.sh /opt/jboss/keycloak/custom-script/entrypoint.sh

ENTRYPOINT ["/bin/sh", "/opt/jboss/keycloak/custom-script/entrypoint.sh"]