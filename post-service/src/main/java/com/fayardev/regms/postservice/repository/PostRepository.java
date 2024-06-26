package com.fayardev.regms.postservice.repository;

import com.fayardev.regms.postservice.entity.Post;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CassandraRepository<Post, String> {

    @Query("SELECT * FROM post WHERE isdeleted = false ALLOW FILTERING")
    Slice<Post> getPosts(Pageable pageable);

    @Query("SELECT * FROM post WHERE isdeleted = false AND userid = ?0 ALLOW FILTERING")
    Slice<Post> getPostsByUserId(String userId, Pageable pageable);
}