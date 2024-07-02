package com.fayardev.regms.commentservice.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDto extends BaseDto {

    private String id;
    private Set<UserDto> following = new HashSet<>();
}