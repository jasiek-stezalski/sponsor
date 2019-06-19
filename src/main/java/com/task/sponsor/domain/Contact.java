package com.task.sponsor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONTACT")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "INFORMAL_NAME")
    private String informalName;

    @Column(name = "CELL_NUMBER", nullable = false)
    private Integer cellNumber;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "BIRTHDAY")
    private LocalDate birthday;

    @Column(name = "JOB_TITLE")
    private String jobTitle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONTACT_ID")
    private Set<Certificate> certificates;

    @OneToMany(mappedBy = "contact", cascade = CascadeType.ALL)
    private List<SponsorContact> sponsorContacts;
}
