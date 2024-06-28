package com.fayardev.regms.likeservice.dto;

import com.fayardev.regms.likeservice.entity.Post;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class LikeDto extends BaseDto {

    private Long id;
    private String userId;
    private Date createdDate;
    private Post post;
}