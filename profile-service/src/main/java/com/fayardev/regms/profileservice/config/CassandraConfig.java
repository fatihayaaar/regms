package com.fayardev.regms.profileservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@RequiredArgsConstructor
@EnableCassandraRepositories(basePackages = "com.fayardev.regms.profileservice.repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    private final Environment environment;

    @Override
    protected String getKeyspaceName() {
        return environment.getProperty("spring.data.cassandra.keyspace");
    }

    @Override
    public SchemaAction getSchemaAction() {
        String schemaAction = environment.getProperty("spring.cassandra.schema-action", "CREATE_IF_NOT_EXISTS");
        return SchemaAction.valueOf(schemaAction);
    }
}
