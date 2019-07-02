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
public class SponsorSummaryDto {
    private Long id;
    private String name;
    private List<SponsorContactSummaryDto> sponsorContacts;

    public SponsorSummaryDto(SponsorContactBasicDetails sponsorDetails) {
        this.name = sponsorDetails.getName();
    }
}


