package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SponsorContactSummaryDto {
    private SponsorSummaryDto sponsor;
    private ContactSummaryDto contact;
    private Boolean primaryContact;
    private Boolean secondaryContact;

    public SponsorContactSummaryDto(SponsorContactBasicDetails sponsorContactDetails, boolean isSponsor, boolean isContact) {
        if (isSponsor) this.sponsor = new SponsorSummaryDto(sponsorContactDetails);
        if (isContact) this.contact = new ContactSummaryDto(sponsorContactDetails);
        this.primaryContact = sponsorContactDetails.getPrimaryContact();
        this.secondaryContact = sponsorContactDetails.getSecondaryContact();
    }
}
