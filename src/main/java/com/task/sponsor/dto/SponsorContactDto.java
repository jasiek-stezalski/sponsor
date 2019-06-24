package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.domain.SponsorContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SponsorContactDto {
    private SponsorDto sponsor;
    private ContactDto contact;
    private LocalDate beginDate;
    private LocalDate endDate;
    private Boolean primaryContact;
    private Boolean secondaryContact;

    public SponsorContactDto(SponsorContact sponsorContact) {
        this.sponsor = new SponsorDto(sponsorContact.getSponsor());
        this.contact = new ContactDto(sponsorContact.getContact());
        this.beginDate = sponsorContact.getBeginDate();
        this.endDate = sponsorContact.getEndDate();
        this.primaryContact = sponsorContact.getPrimaryContact();
        this.secondaryContact = sponsorContact.getSecondaryContact();
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

    @Data
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public class SponsorDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private String description;
        private Boolean active;
        private String imageId;
        private String websiteUrl;

        public SponsorDto(Sponsor sponsor) {
            this.id = sponsor.getId();
            this.name = sponsor.getName();
            this.phoneNumber = sponsor.getPhoneNumber();
            this.description = sponsor.getDescription();
            this.active = sponsor.getActive();
            this.imageId = sponsor.getImageId();
            this.websiteUrl = sponsor.getWebsiteUrl();
        }
    }
}
