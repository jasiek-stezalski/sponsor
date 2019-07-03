package com.task.sponsor.domain;

import com.task.sponsor.common.SponsorContactId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    @Column(name = "PRIMARY_CONTACT")
    private Boolean primaryContact;

    @Column(name = "SECONDARY_CONTACT")
    private Boolean secondaryContact;
}