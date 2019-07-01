package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.domain.Address;
import com.task.sponsor.domain.ProductCategory;
import com.task.sponsor.domain.SocialMediaLink;
import com.task.sponsor.domain.Sponsor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SponsorDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private String description;
    private Boolean active;
    private String imageId;
    private String websiteUrl;
    private Address address;
    private Set<SocialMediaLink> socialMediaLinks;
    private Set<ProductCategory> productCategories;
    private List<SponsorContactDto> sponsorContacts;

    public SponsorDto(Sponsor sponsor, boolean isSponsorContact) {
        this.id = sponsor.getId();
        this.name = sponsor.getName();
        this.phoneNumber = sponsor.getPhoneNumber();
        this.description = sponsor.getDescription();
        this.active = sponsor.getActive();
        this.imageId = sponsor.getImageId();
        this.websiteUrl = sponsor.getWebsiteUrl();
        this.address = sponsor.getAddress();
        this.socialMediaLinks = sponsor.getSocialMediaLinks();
        this.productCategories = sponsor.getProductCategories();
        if (isSponsorContact)
            this.sponsorContacts = sponsor.getSponsorContacts().stream().map(sp -> new SponsorContactDto(sp, false, true)).collect(Collectors.toList());
    }
}


