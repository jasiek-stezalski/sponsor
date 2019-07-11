package com.task.sponsor.material.service;

import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.material.converter.FileConverter;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.dto.FileDto;
import com.task.sponsor.material.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final FileRepository repository;
    private final FileConverter converter;

    public FileService(FileRepository repository, FileConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public FileDto save(File file) {
        return converter.convert(repository.save(file));
    }

    public FileDto update(File file) {
        repository.findById(file.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Conference call with id: " + file.getId()));
        return converter.convert(repository.save(file));
    }

    public FileDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no File call with id: " + id)));
    }

    public List<FileDto> findAll() {
        return repository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
