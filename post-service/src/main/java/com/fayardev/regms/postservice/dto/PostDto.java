package com.fayardev.regms.postservice.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PostDto extends BaseDto {

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
