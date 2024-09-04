FROM osixia/openldap:latest

RUN apt-get update || true
RUN apt-get install -y --no-install-recommends wget && \
    rm -rf /var/lib/apt/lists/*

ENV LDAP_ORGANISATION=${LDAP_ORGANISATION}
ENV LDAP_DOMAIN=${LDAP_DOMAIN}

ENTRYPOINT ["/bin/sh", "/container/documents/script/entrypoint.sh"]