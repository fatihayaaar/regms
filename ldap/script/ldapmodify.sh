#!/bin/bash

ldapmodify -Y EXTERNAL -H ldapi:/// -f /container/documents/ldif/custom-user-details.ldif
ldapmodify -Y EXTERNAL -H ldapi:/// -f /container/documents/ldif/configure-unique-uuid.ldif
ldapmodify -Y EXTERNAL -H ldapi:/// -f /container/documents/ldif/import.ldif