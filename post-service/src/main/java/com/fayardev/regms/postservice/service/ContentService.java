package com.fayardev.regms.postservice.service;

import com.fayardev.regms.postservice.dto.ContentDTO;
import com.fayardev.regms.postservice.entity.content.Content;
import com.fayardev.regms.postservice.repository.ContentRepository;
import com.fayardev.regms.postservice.service.abstracts.IContentService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ContentService implements IContentService<ContentDTO> {

    private final ContentRepository repository;
    private final ModelMapper modelMapper;

    public ContentService(ContentRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ContentDTO add(ContentDTO contentDTO) {
        Content content = repository.save(modelMapper.map(contentDTO, Content.class));
        contentDTO.setId(content.getId());
        return contentDTO;
    }

    @Override
    public void delete(UUID uuid) {
        Content content = repository.findById(uuid).orElseThrow(IllegalArgumentException::new);
        repository.delete(content);
    }

    @Override
    public ContentDTO update(ContentDTO contentDTO) {
        Optional<Content> content = repository.findById(contentDTO.getId());
        Content contentUpdated = content.map(it -> {
            it.setContentType(contentDTO.getContentType());
            it.setText(contentDTO.getText());
            it.setUri(contentDTO.getUri());
            return it;
        }).orElseThrow(IllegalAccessError::new);
        repository.save(contentUpdated);
        return modelMapper.map(contentUpdated, ContentDTO.class);
    }

    @Override
    public ContentDTO get(UUID uuid) {
        return modelMapper.map(repository.findById(uuid).orElseThrow(IllegalAccessError::new), ContentDTO.class);
    }

    @Override
    public Slice<ContentDTO> getAll(Pageable pageable) {
        return null;
    }
}
