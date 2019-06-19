package com.task.sponsor.converter;

import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import org.springframework.stereotype.Component;

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
        return SponsorDto.builder()
                .id(sponsor.getId())
                .name(sponsor.getName())
                .phoneNumber(sponsor.getPhoneNumber())
                .description(sponsor.getDescription())
                .active(sponsor.getActive())
                .imageId(sponsor.getImageId())
                .websiteUrl(sponsor.getWebsiteUrl())
                .build();
    }
}