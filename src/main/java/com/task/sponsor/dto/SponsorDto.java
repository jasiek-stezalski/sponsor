package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.domain.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    public SponsorDto(Sponsor sponsor) {
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
        this.sponsorContacts = sponsor.getSponsorContacts().stream().map(SponsorContactDto::new).collect(Collectors.toList());
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public class SponsorContactDto {
        private ContactDto contact;
        private LocalDate beginDate;
        private LocalDate endDate;
        private Boolean primaryContact;
        private Boolean secondaryContact;

        public SponsorContactDto(SponsorContact sponsorContact) {
            this.contact = new ContactDto(sponsorContact.getContact());
            this.beginDate = sponsorContact.getBeginDate();
            this.endDate = sponsorContact.getEndDate();
            this.primaryContact = sponsorContact.getPrimaryContact();
            this.secondaryContact = sponsorContact.getSecondaryContact();
        }
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public class ContactDto {
        private Long id;
        private String firstName;
        private String lastName;
        private String informalName;
        private Integer cellNumber;
        private String email;
        private LocalDate birthday;
        private String jobTitle;

        public ContactDto(Contact contact) {
            this.id = contact.getId();
            this.firstName = contact.getFirstName();
            this.lastName = contact.getLastName();
            this.informalName = contact.getInformalName();
            this.cellNumber = contact.getCellNumber();
            this.email = contact.getEmail();
            this.birthday = contact.getBirthday();
            this.jobTitle = contact.getJobTitle();
        }
    }
}


