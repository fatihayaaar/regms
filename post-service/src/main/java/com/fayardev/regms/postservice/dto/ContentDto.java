package com.fayardev.regms.postservice.dto;

import com.fayardev.regms.postservice.entity.content.ContentType;
import lombok.*;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class ContentDto extends BaseDto {

    private UUID id;
    private ContentType contentType;
    private String uri;
    private String text;
}
