package com.fayardev.regms.postservice.dto;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PostDto extends BaseDto {

    private UUID id;
    private UUID userId;
    private int contentId;
    private Date createdDate;
    private Date updatedDate;
    private int likeCount;
    private int commentCount;
    private boolean visible;
}
