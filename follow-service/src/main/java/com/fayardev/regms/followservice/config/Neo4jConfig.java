package com.fayardev.regms.followservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.data.neo4j.core.Neo4jTemplate;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "com.fayardev.regms.followservice.repository")
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public Neo4jTemplate neo4jTemplate(Neo4jClient neo4jClient) {
        return new Neo4jTemplate(neo4jClient);
    }
}