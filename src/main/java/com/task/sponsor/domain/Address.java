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


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CITY")
    private String city;

    @Column(name = "LINE_ONE")
    private String lineOne;

    @Column(name = "LINE_TWO")
    private String lineTwo;

    @Column(name = "STATE")
    private String STATE;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "ADDITIONAL_INFO")
    private String additionalInfo;
}
