package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactSummaryDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String informalName;
    private List<SponsorContactSummaryDto> sponsorContacts;

    public ContactSummaryDto(SponsorContactBasicDetails contactDetails) {
        this.firstName = contactDetails.getFirstName();
        this.lastName = contactDetails.getLastName();
        this.informalName = contactDetails.getInformalName();
    }
}
