package com.fayardev.regms.userservice.dtos;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserFollowerDto extends BaseDto {

    private String id;
    private Set<UserFollowerDto> following = new HashSet<>();
}