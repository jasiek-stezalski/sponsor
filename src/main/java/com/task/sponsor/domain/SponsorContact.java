package com.task.sponsor.domain;

import com.task.sponsor.common.CompositeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(CompositeKey.class)
@Table(name = "SPONSOR_CONTACT")
public class SponsorContact {

    @Id
    @ManyToOne
    @JoinColumn(name = "SPONSOR_ID")
    private Sponsor sponsor;

    @Id
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;

    @Column(name = "BEGIN_DATE")
    private LocalDate beginDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;
}