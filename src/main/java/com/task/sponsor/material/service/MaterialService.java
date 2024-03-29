package com.task.sponsor.material.service;

import com.task.sponsor.exception.DataConflictException;
import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.material.converter.MaterialConverter;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.domain.Material;
import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.MaterialDto;
import com.task.sponsor.material.repository.MaterialRepository;
import org.springframework.stereotype.Service;

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

    public MaterialDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Material with id: " + id)));
    }

    public List<MaterialDto> findAll() {
        return repository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }

    public MaterialDto addSupportMaterial(Long materialId, Long supportId) {
        Material material = repository.findById(materialId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Material with id: " + materialId));
        Material supportMaterial = repository.findById(supportId)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Material with id: " + supportId));

        if (supportMaterial instanceof File || supportMaterial instanceof Website) {
            supportMaterial.setMaterial(material);
            return converter.convert(repository.save(supportMaterial));
        } else {
            throw new DataConflictException("Support material can be only File or Website!");
        }
    }

}
