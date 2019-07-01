package com.task.sponsor.domain;

import com.task.sponsor.common.SponsorContactId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SPONSOR_CONTACT")
public class SponsorContact {

    @EmbeddedId
    private SponsorContactId id;

    @ManyToOne
    @MapsId("sponsorId")
    private Sponsor sponsor;

    @ManyToOne
    @MapsId("contactId")
    private Contact contact;

    @Column(name = "BEGIN_DATE", nullable = false)
    private LocalDate beginDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "PRIMARY_CONTACT")
    private Boolean primaryContact;

    @Column(name = "SECONDARY_CONTACT")
    private Boolean secondaryContact;
}