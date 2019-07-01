package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public SponsorContactDto(SponsorContact sponsorContact, boolean isSponsor, boolean isContact) {
        if (isSponsor) this.sponsor = new SponsorDto(sponsorContact.getSponsor(), false);
        if (isContact) this.contact = new ContactDto(sponsorContact.getContact(), false);
        this.beginDate = sponsorContact.getBeginDate();
        this.endDate = sponsorContact.getEndDate();
        this.primaryContact = sponsorContact.getPrimaryContact();
        this.secondaryContact = sponsorContact.getSecondaryContact();
    }
}
