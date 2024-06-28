package com.fayardev.regms.likeservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Node
public class Post extends BaseEntity {

    @Id
    private String id;
    private String userId;
}
