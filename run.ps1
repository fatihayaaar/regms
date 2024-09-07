$microservices = @(
    "config-server",
    "eureka-server",
    "gateway-server",
    "user-service",
    "profile-service",
    "post-service",
    "follow-service",
    "like-service",
    "comment-service",
    "feed-service",
    "admin-server"
)

foreach ($microservice in $microservices) {
    & ./gradlew ":$microservice:build"
}

Start-Process "docker-compose" -ArgumentList "up -d"
Start-Sleep -Seconds 3

do {
    $output = docker exec cassandra cqlsh -e "SELECT table_name FROM system_schema.tables;" 2>&1
    Start-Sleep -Seconds 3
} while ($output -match "Connection refused")

docker exec cassandra cqlsh -f /docker-entrypoint-initdb.d/schema.cql

docker-compose -f ./regms-client/docker-compose.yml up -d