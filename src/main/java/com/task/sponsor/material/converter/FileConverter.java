package com.task.sponsor.material.converter;

import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.dto.FileDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class FileConverter {

    private final MaterialConverter converter;

    public FileConverter(MaterialConverter converter) {
        this.converter = converter;
    }

    public FileDto convert(File file) {
        FileDto fileDto = FileDto.builder()
                .id(file.getId())
                .title(file.getTitle())
                .sponsorId(file.getSponsorId())
                .fileUuid(file.getFileUuid())
                .build();

        if (file.getMaterial() != null) {
            fileDto.setMaterial(converter.convert(file.getMaterial()));
        }

        if (!CollectionUtils.isEmpty(file.getProductTypes())) {
            fileDto.setProductTypes(file.getProductTypes());
        }

        if (!CollectionUtils.isEmpty(file.getSupportingMaterials())) {
            fileDto.setSupportingMaterials(file.getSupportingMaterials().stream().map(converter::convert).collect(Collectors.toSet()));
        }

        return fileDto;
    }

}
