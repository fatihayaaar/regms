package com.fayardev.regms.commentservice.repository;

import com.fayardev.regms.commentservice.entity.Comment;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends Neo4jRepository<Comment, Long> {

    @Query("MATCH (p:Post)<-[c:COMMENTED_ON]-(:Comment) WHERE p.id = $postId RETURN count(c)")
    long countCommentsByPostId(String postId);

    @Query("MATCH (p:Post)<-[c:COMMENTED_ON]-(:Comment) WHERE p.id = $postId RETURN c.userId, c.text, c.createdDate")
    List<Comment> findCommentsByPostId(String postId);

    @Query("MATCH (p:Post {id: $postId})<-[:COMMENTED_ON]-(c:Comment {userId: $userId}) RETURN count(c) > 0")
    boolean isCommentedByUser(String postId, String userId);

    @Transactional
    @Query("MATCH (p:Post {id: $postId})<-[:COMMENTED_ON]-(c:Comment {userId: $userId}) DETACH DELETE c")
    void deleteByPostIdAndUserId(String postId, String userId);
}
