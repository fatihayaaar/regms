#!/bin/bash

microservices=(
    "config-server"
    "eureka-server"
    "gateway-server"
    "user-service"
    "profile-service"
)

for microservice in "${microservices[@]}"
do
  ./gradlew :"$microservice":build
done