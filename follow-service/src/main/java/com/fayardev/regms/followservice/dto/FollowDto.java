package com.fayardev.regms.followservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class FollowDto extends BaseDto {
    private UUID uuid;
    private UUID followerId;
    private UUID followingId;
}