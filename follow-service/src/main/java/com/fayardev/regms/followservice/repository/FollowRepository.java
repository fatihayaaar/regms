package com.fayardev.regms.followservice.repository;

import com.fayardev.regms.followservice.entity.Follow;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowRepository extends CassandraRepository<Follow, UUID> {
}
