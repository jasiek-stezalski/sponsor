package com.task.sponsor.material.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.material.domain.ConferenceCall;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ConferenceCallDto extends MaterialDto {
    private String conferenceCallDtm;
    private String dialInNumber;
    private String code;
    private String description;

    @Builder
    public ConferenceCallDto(Long id, String title, MaterialDto material, Set<MaterialDto> supportingMaterials, Long sponsorId, String conferenceCallDtm, String dialInNumber, String code, String description) {
        super(id, title, material, supportingMaterials, sponsorId);
        this.conferenceCallDtm = conferenceCallDtm;
        this.dialInNumber = dialInNumber;
        this.code = code;
        this.description = description;
    }

    public ConferenceCallDto(ConferenceCall conferenceCall) {
        this.setId(conferenceCall.getId());
        this.setTitle(conferenceCall.getTitle());
        this.setConferenceCallDtm(conferenceCall.getConferenceCallDtm());
        this.setDialInNumber(conferenceCall.getDialInNumber());
        this.setCode(conferenceCall.getCode());
        this.setDescription(conferenceCall.getDescription());
    }
}
