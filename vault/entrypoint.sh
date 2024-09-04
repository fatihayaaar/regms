#!/bin/bash

vault server -dev -dev-listen-address="0.0.0.0:8200" &

while true; do
  if wget -qO- http://127.0.0.1:8200/v1/sys/health | grep -q '"sealed":false'; then
    echo "Vault is up and running."
    break
  else
    sleep 1
  fi
done

vault login admin

#openldap
vault kv put secret/ldap password="admin"

tail -f /dev/null