#!/bin/bash

microservices=(
    "config-server"
    "eureka-server"
    "gateway-server"
    "user-service"
    "profile-service"
    "file-service"
    "post-service"
    "follow-service"
    "feed-service"
)

for microservice in "${microservices[@]}"
do
  ./gradlew :"$microservice":build
done