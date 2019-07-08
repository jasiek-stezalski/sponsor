package com.task.sponsor.material.converter;

import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.WebsiteDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class WebsiteConverter {

    private final MaterialConverter converter;

    public WebsiteConverter(MaterialConverter converter) {
        this.converter = converter;
    }

    public WebsiteDto convert(Website website) {
        WebsiteDto websiteDto = WebsiteDto.builder()
                .id(website.getId())
                .title(website.getTitle())
                .url(website.getUrl())
                .build();

        if (website.getMaterial() != null) {
            websiteDto.setMaterial(converter.convert(website.getMaterial()));
        }

        if (!CollectionUtils.isEmpty(website.getSupportingMaterials())) {
            websiteDto.setSupportingMaterials(website.getSupportingMaterials().stream().map(converter::convert).collect(Collectors.toSet()));
        }

        return websiteDto;
    }

}
