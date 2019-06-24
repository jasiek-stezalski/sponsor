package com.task.sponsor.converter;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.domain.SponsorContact;
import com.task.sponsor.dto.SponsorContactDto;
import org.springframework.stereotype.Component;

@Component
public class SponsorContactConverter {

    public SponsorContact convert(SponsorContactDto sponsorContactDto) {
        SponsorContact sponsorContact = SponsorContact.builder()
                .beginDate(sponsorContactDto.getBeginDate())
                .endDate(sponsorContactDto.getEndDate())
                .primaryContact(sponsorContactDto.getPrimaryContact())
                .secondaryContact(sponsorContactDto.getSecondaryContact())
                .build();

        if (sponsorContact.getSponsor() != null) {
            sponsorContact.setSponsor(sponsorContact.getSponsor());
        }

        if (sponsorContact.getContact() != null) {
            sponsorContact.setContact(sponsorContact.getContact());
        }

        return sponsorContact;
    }

    public SponsorContactDto convert(SponsorContact sponsorContact) {
        SponsorContactDto sponsorContactDto = SponsorContactDto.builder()
                .beginDate(sponsorContact.getBeginDate())
                .endDate(sponsorContact.getEndDate())
                .primaryContact(sponsorContact.getPrimaryContact())
                .secondaryContact(sponsorContact.getSecondaryContact())
                .build();

        if (sponsorContact.getSponsor() != null) {
            sponsorContactDto.setSponsor(new SponsorContactDto().new SponsorDto(sponsorContact.getSponsor()));
        }

        if (sponsorContact.getContact() != null) {
            sponsorContactDto.setContact(new SponsorContactDto().new ContactDto(sponsorContact.getContact()));
        }

        return sponsorContactDto;
    }

    private Sponsor convert(SponsorContactDto.SponsorDto sponsorDto) {
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

    public Contact convert(SponsorContactDto.ContactDto contactDto) {
        return Contact.builder()
                .id(contactDto.getId())
                .firstName(contactDto.getFirstName())
                .lastName(contactDto.getLastName())
                .informalName(contactDto.getInformalName())
                .cellNumber(contactDto.getCellNumber())
                .email(contactDto.getEmail())
                .birthday(contactDto.getBirthday())
                .jobTitle(contactDto.getJobTitle())
                .build();
    }

}
