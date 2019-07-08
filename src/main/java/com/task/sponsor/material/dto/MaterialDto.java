package com.task.sponsor.material.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.domain.Website;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = NAME, include = PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value=FileDto.class, name = "File"),
        @JsonSubTypes.Type(value= Website.class, name = "Website"),
        @JsonSubTypes.Type(value= ConferenceCall.class, name = "ConferenceCall")
})
public abstract class MaterialDto {
    private Long id;
    private String title;
    private MaterialDto material;
    private Set<MaterialDto> supportingMaterials;
}
