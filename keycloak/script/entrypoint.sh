#!/bin/sh

curl -s -H "X-Vault-Token: ${VAULT_TOKEN}" http://vault:8200/v1/secret/data/ldap -o /tmp/ldap_response.json

LDAP_BIND_CREDENTIAL=$(grep -o '"password":"[^"]*"' /tmp/ldap_response.json | sed 's/"password":"\([^"]*\)"/\1/')
export LDAP_BIND_CREDENTIAL

 /opt/jboss/keycloak/bin/standalone.sh -b 0.0.0.0 \
     -Dkeycloak.import=/opt/jboss/keycloak/standalone/configuration/realm-file.json \
     -Dkeycloak.migration.action=import \
     -Dkeycloak.migration.provider=singleFile \
     -Dkeycloak.migration.file=/opt/jboss/keycloak/standalone/configuration/realm-file.json \
     -Dkeycloak.migration.strategy=OVERWRITE_EXISTING \
     -Dkeycloak.hostname.fixed.hostname=keycloak \
     "$@" &
KEYCLOAK_PID=$!

/opt/jboss/keycloak/bin/add-user-keycloak.sh -u "${KEYCLOAK_USER}" -p "${KEYCLOAK_PASSWORD}"

wait $KEYCLOAK_PID