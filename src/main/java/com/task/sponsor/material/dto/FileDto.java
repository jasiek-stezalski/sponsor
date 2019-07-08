package com.task.sponsor.material.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.domain.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class FileDto extends MaterialDto{
    private Long fileUuid;
    private Set<ProductType> productTypes;

    @Builder
    public FileDto(Long id, String title, MaterialDto material, Set<MaterialDto> supportingMaterials, Long fileUuid, Set<ProductType> productTypes) {
        super(id, title, material, supportingMaterials);
        this.fileUuid = fileUuid;
        this.productTypes = productTypes;
    }

    public FileDto(File file) {
        this.setId(file.getId());
        this.setTitle(file.getTitle());
        this.setFileUuid(file.getFileUuid());
        this.setProductTypes(file.getProductTypes());
    }
}
