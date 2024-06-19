package com.fayardev.regms.postservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("post")
public class Post extends BaseEntity {

    @PrimaryKey
    private String id;
    private String userId;
    private String uri;
    private String text;
    private Date createdDate;
    private Date updatedDate;
    private int likeCount;
    private int commentCount;
    private boolean visible;
    private boolean isDeleted;
}
