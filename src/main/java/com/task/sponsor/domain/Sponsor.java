package com.task.sponsor.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Column;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SPONSOR")
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "IMAGE_ID")
    private String imageId;

    @Column(name = "WEBSITE_URL")
    private String websiteUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SPONSOR_ID")
    private Set<SocialMediaLink> socialMediaLinks;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "SPONSOR_ID")
    private Set<ProductCategory> productCategories;

    @OneToMany(mappedBy = "sponsor", cascade = CascadeType.ALL)
    private List<SponsorContact> sponsorContacts;
}
