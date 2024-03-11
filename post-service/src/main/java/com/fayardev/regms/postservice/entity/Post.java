package com.fayardev.regms.postservice.entity;

import com.fayardev.regms.postservice.entity.content.Content;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("post")
public class Post extends BaseEntity {

    @PrimaryKey
    private UUID id;
    private UUID userId;
    private int contentId;
    private Date createdDate;
    private Date updatedDate;
    private int likeCount;
    private int commentCount;
    private boolean visible;
}
