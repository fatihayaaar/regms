#!/bin/bash

docker exec -it openldap /bin/bash -c "ldapmodify -Y EXTERNAL -H ldapi:/// -f /container/documents/ldif/custom-user-details.ldif"