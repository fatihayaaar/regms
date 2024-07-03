package com.fayardev.regms.commentservice.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class CommentDto extends BaseDto {

    private Long id;
    private String userId;
    private String text;
    private Date createdDate;
    private String avatar;
    private String username;
    private PostDto post;
}