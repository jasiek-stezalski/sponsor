package com.task.sponsor.material.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.material.domain.Website;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class WebsiteDto extends MaterialDto {
    private String url;

    @Builder
    public WebsiteDto(Long id, String title, MaterialDto material, Set<MaterialDto> supportingMaterials, String url) {
        super(id, title, material, supportingMaterials);
        this.url = url;
    }

    public WebsiteDto(Website website) {
        this.setId(website.getId());
        this.setTitle(website.getTitle());
        this.setUrl(website.getUrl());
    }
}
