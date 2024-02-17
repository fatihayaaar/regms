package com.fayardev.regms.followservice.dto;

import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FollowDTO {
    private UUID followerId;
    private UUID followingId;
}