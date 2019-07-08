package com.task.sponsor.material.service;

import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.material.converter.MaterialConverter;
import com.task.sponsor.material.domain.Material;
import com.task.sponsor.material.dto.MaterialDto;
import com.task.sponsor.material.repository.MaterialRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialService {

    private final MaterialRepository repository;
    private final MaterialConverter converter;

    public MaterialService(MaterialRepository repository, MaterialConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public MaterialDto save(Material material) {
        return converter.convert(repository.save(material));
    }

    public MaterialDto update(Material material) {
        repository.findById(material.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Material with id: " + material.getId()));
        return converter.convert(repository.save(material));
    }

    @Transactional
    public MaterialDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Material with id: " + id)));
    }

    @Transactional
    public List<MaterialDto> findAll() {
        return repository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
