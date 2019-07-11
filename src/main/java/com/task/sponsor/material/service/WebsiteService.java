package com.task.sponsor.material.service;

import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.material.converter.WebsiteConverter;
import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.WebsiteDto;
import com.task.sponsor.material.repository.WebsiteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebsiteService {

    private final WebsiteRepository repository;
    private final WebsiteConverter converter;

    public WebsiteService(WebsiteRepository repository, WebsiteConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public WebsiteDto save(Website website) {
        return converter.convert(repository.save(website));
    }

    public WebsiteDto update(Website website) {
        repository.findById(website.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Website with id: " + website.getId()));
        return converter.convert(repository.save(website));
    }

    public WebsiteDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Website with id: " + id)));
    }

    public List<WebsiteDto> findAll() {
        return repository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
