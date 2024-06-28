package com.fayardev.regms.likeservice.entity;

import lombok.*;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Node
public class Like extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String userId;
    private Date createdDate;

    @Relationship(type = "LIKED_POST")
    private Post post;
}