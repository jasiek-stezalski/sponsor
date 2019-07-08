package com.task.sponsor.material.converter;

import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.dto.ConferenceCallDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class ConferenceCallConverter {

    private final MaterialConverter converter;

    public ConferenceCallConverter(MaterialConverter converter) {
        this.converter = converter;
    }

    public ConferenceCallDto convert(ConferenceCall conferenceCall) {
        ConferenceCallDto conferenceCallDto = ConferenceCallDto.builder()
                .id(conferenceCall.getId())
                .title(conferenceCall.getTitle())
                .conferenceCallDtm(conferenceCall.getConferenceCallDtm())
                .dialInNumber(conferenceCall.getDialInNumber())
                .code(conferenceCall.getCode())
                .description(conferenceCall.getDescription())
                .build();

        if (conferenceCall.getMaterial() != null) {
            conferenceCallDto.setMaterial(converter.convert(conferenceCall.getMaterial()));
        }

        if (!CollectionUtils.isEmpty(conferenceCall.getSupportingMaterials())) {
            conferenceCallDto.setSupportingMaterials(conferenceCall.getSupportingMaterials().stream().map(converter::convert).collect(Collectors.toSet()));
        }

        return conferenceCallDto;
    }

}
