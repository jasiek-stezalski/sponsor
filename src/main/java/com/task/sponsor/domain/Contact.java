package com.task.sponsor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;


@Data
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
}
