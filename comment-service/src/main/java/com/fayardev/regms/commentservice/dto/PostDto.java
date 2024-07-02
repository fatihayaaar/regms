package com.fayardev.regms.commentservice.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class PostDto extends BaseDto {

    private String id;
    private String userId;
}
