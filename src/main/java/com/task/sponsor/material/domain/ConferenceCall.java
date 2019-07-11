package com.task.sponsor.material.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONFERENCE_CALL")
public class ConferenceCall extends Material {

    @Column(name = "CONFERENCE_CALL_DTM")
    private String conferenceCallDtm;

    @Column(name = "DIAL_IN_NUMBER")
    private String dialInNumber;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;
}
