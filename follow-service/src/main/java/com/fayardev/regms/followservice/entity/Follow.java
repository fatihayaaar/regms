package com.fayardev.regms.followservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("follow")
public class Follow extends BaseEntity {
    @PrimaryKey
    private UUID uuid;
    private UUID followerId;
    private UUID followingId;
}
