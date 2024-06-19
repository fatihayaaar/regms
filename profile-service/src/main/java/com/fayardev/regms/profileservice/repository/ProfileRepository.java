package com.fayardev.regms.profileservice.repository;

import com.fayardev.regms.profileservice.entity.Profile;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CassandraRepository<Profile, String> {

    Optional<Profile> getProfilesByUserId(String userId);
}