package com.task.sponsor.converter;

import com.task.sponsor.domain.SponsorContact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.dto.SponsorContactDto;
import com.task.sponsor.dto.SponsorDto;
import org.springframework.stereotype.Component;

@Component
public class SponsorContactConverter {

    public SponsorContact convert(SponsorContactDto sponsorContactDto) {
        SponsorContact sponsorContact = SponsorContact.builder()
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
                .endDate(sponsorContact.getEndDate())
                .primaryContact(sponsorContact.getPrimaryContact())
                .secondaryContact(sponsorContact.getSecondaryContact())
                .build();

        if (sponsorContact.getSponsor() != null) {
            sponsorContactDto.setSponsor(new SponsorDto(sponsorContact.getSponsor(), true));
        }

        if (sponsorContact.getContact() != null) {
            sponsorContactDto.setContact(new ContactDto(sponsorContact.getContact(), true));
        }

        return sponsorContactDto;
    }

}
