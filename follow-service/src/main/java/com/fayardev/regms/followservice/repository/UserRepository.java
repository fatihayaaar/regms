package com.fayardev.regms.followservice.repository;

import com.fayardev.regms.followservice.entity.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface UserRepository extends Neo4jRepository<User, String> {
}
