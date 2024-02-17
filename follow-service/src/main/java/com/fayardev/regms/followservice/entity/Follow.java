package com.fayardev.regms.followservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("follow")
public class Follow {
    @PrimaryKey
    private UUID followerId;
    private UUID followingId;
}
