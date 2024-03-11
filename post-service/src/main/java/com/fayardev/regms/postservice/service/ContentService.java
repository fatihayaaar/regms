package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.dto.ContentDto;
import com.fayardev.regms.postservice.entity.content.Content;
import com.fayardev.regms.postservice.repository.ContentRepository;
import com.fayardev.regms.postservice.service.abstracts.IContentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContentService implements IContentService<ContentDto> {

    private final ContentRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public ContentDto add(ContentDto contentDTO) {
        Content content = repository.save(modelMapper.map(contentDTO, Content.class));
        contentDTO.setId(content.getId());
        return contentDTO;
    }

    @Override
    public void delete(UUID id) {
        Content content = repository.findById(id).orElseThrow(IllegalArgumentException::new);
        repository.delete(content);
    }

    @Override
    public ContentDto update(ContentDto contentDTO) {
        Optional<Content> content = repository.findById(contentDTO.getId());
        Content contentUpdated = content.map(it -> {
            it.setContentType(contentDTO.getContentType());
            it.setText(contentDTO.getText());
            it.setUri(contentDTO.getUri());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(contentUpdated);
        return modelMapper.map(contentUpdated, ContentDto.class);
    }

    @Override
    public ContentDto get(UUID id) {
        return modelMapper.map(repository.findById(id).orElseThrow(IllegalAccessError::new), ContentDto.class);
    }

    @Override
    public Slice<ContentDto> getAll(Pageable pageable) {
        return null;
    }
}
