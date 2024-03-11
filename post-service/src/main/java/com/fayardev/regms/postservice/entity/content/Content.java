package com.fayardev.regms.postservice.entity.content;

import com.fayardev.regms.postservice.entity.BaseEntity;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
@Table("content")
public class Content extends BaseEntity {

    @PrimaryKey
    private UUID id;
    private ContentType contentType;
    private String uri;
    private String text;
}
