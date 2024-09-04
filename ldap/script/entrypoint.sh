#!/bin/sh

wget -qO- --header="X-Vault-Token: ${VAULT_TOKEN}" http://vault:8200/v1/secret/data/ldap > /tmp/ldap_response.json
LDAP_ADMIN_PASSWORD=$(jq -r '.data.data.password' /tmp/ldap_response.json)

export LDAP_ADMIN_PASSWORD

/container/tool/run &
TOOL_PID=$!

while true; do
  if ldapsearch -x -H ldap://localhost:389 -b "" -s base "(objectclass=*)" > /dev/null 2>&1; then
    /container/documents/script/ldapmodify.sh
    break
  else
    sleep 1
  fi
done

wait $TOOL_PID