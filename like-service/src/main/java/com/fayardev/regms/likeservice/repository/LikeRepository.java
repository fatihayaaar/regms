package com.fayardev.regms.likeservice.repository;

import com.fayardev.regms.likeservice.entity.Like;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LikeRepository extends Neo4jRepository<Like, Long> {

    @Query("MATCH (p:Post)<-[l:LIKED_POST]-(:Like) WHERE p.id = $postId RETURN count(l)")
    long countLikesByPostId(String postId);

    @Query("MATCH (p:Post)<-[l:LIKED_POST]-(:Like) WHERE p.id = $postId RETURN l.userId")
    List<String> findUsersWhoLikedPost(String postId);

    @Query("MATCH (p:Post {id: $postId})<-[:LIKED_POST]-(l:Like {userId: $userId}) RETURN count(l) > 0")
    boolean isPostLikedByUser(@Param("postId") String postId, @Param("userId") String userId);

    @Transactional
    @Query("MATCH (p:Post {id: $postId})<-[:LIKED_POST]-(l:Like {userId: $userId}) DETACH DELETE l")
    void deleteByPostIdAndUserId(String postId, String userId);
}
