package com.fayardev.regms.postservice.repository;

import com.fayardev.regms.postservice.entity.content.Content;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentRepository extends CassandraRepository<Content, UUID> {
}
