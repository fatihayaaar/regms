package com.fayardev.regms.followservice.repository;

import com.fayardev.regms.followservice.entity.User;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;

public interface UserRepository extends Neo4jRepository<User, String> {

    @Query("MATCH (u:User {id: $userId})-[:FOLLOWS]->(f:User {id: $followingId}) RETURN COUNT(f) > 0")
    boolean isFollowing(String userId, String followingId);

    @Query("MATCH (u:User {id: $userId})-[r:FOLLOWS]->(f:User {id: $followingId}) DELETE r RETURN COUNT(r) > 0")
    boolean unfollow(String userId, String followingId);

    @Query("MATCH (u:User {id: $userId})-[:FOLLOWS]->(f) RETURN COUNT(f)")
    int countFollowees(String userId);

    @Query("MATCH (u:User)<-[:FOLLOWS]-(f) WHERE u.id = $userId RETURN COUNT(f)")
    int countFollowers(String userId);

    @Query("MATCH (u:User {id: $userId})-[:FOLLOWS]->(f:User) RETURN f")
    List<User> findFollowees(String userId);

    @Query("MATCH (u:User)<-[:FOLLOWS]-(f:User) WHERE u.id = $userId RETURN f")
    List<User> findFollowers(String userId);
}
