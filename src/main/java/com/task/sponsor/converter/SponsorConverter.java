package com.task.sponsor.converter;

import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class SponsorConverter {

    public Sponsor convert(SponsorDto sponsorDto) {
        return Sponsor.builder()
                .id(sponsorDto.getId())
                .name(sponsorDto.getName())
                .phoneNumber(sponsorDto.getPhoneNumber())
                .description(sponsorDto.getDescription())
                .active(sponsorDto.getActive())
                .imageId(sponsorDto.getImageId())
                .websiteUrl(sponsorDto.getWebsiteUrl())
                .build();
    }

    public SponsorDto convert(Sponsor sponsor) {
        SponsorDto sponsorDto = SponsorDto.builder()
                .id(sponsor.getId())
                .name(sponsor.getName())
                .phoneNumber(sponsor.getPhoneNumber())
                .description(sponsor.getDescription())
                .active(sponsor.getActive())
                .imageId(sponsor.getImageId())
                .websiteUrl(sponsor.getWebsiteUrl())
                .build();

        if (sponsor.getAddress() != null) {
            sponsorDto.setAddress(sponsor.getAddress());
        }

        if (!CollectionUtils.isEmpty(sponsor.getSocialMediaLinks())) {
            sponsorDto.setSocialMediaLinks(sponsor.getSocialMediaLinks());
        }

        if (!CollectionUtils.isEmpty(sponsor.getProductCategories())) {
            sponsorDto.setProductCategories(sponsor.getProductCategories());
        }

        if (!CollectionUtils.isEmpty(sponsor.getSponsorContacts())) {
            sponsorDto.setSponsorContacts(sponsor.getSponsorContacts().stream()
                    .map(sponsorContract -> new SponsorDto(sponsor).new SponsorContactDto(sponsorContract))
                    .collect(Collectors.toList()));
        }
        return sponsorDto;
    }
}
