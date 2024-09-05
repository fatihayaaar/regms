#!/bin/bash

microservices=(
    "config-server"
    "eureka-server"
    "gateway-server"
    "user-service"
    "profile-service"
    "post-service"
    "follow-service"
    "like-service"
    "comment-service"
    "feed-service"
    "admin-server"
)

for microservice in "${microservices[@]}"
do
  ./gradlew :"$microservice":build
done

docker-compose up &
(
  until docker exec cassandra cqlsh -e "SELECT table_name FROM system_schema.tables;" > /dev/null 2>&1; do
    sleep 3
  done
  docker exec cassandra cqlsh -f /docker-entrypoint-initdb.d/schema.cql
) &