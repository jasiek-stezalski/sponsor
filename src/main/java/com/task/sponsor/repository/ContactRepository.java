package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT c.id AS id, c.firstName AS firstName, c.lastName AS lastName, c.informalName AS informalName, " +
            "sc.beginDate AS beginDate, sc.primaryContact AS primaryContact, sc.secondaryContact AS secondaryContact, s.name AS name " +
            "FROM Contact c " +
            "LEFT JOIN c.sponsorContacts sc ON sc.endDate IS NULL AND sc.primaryContact = 1 AND sc.secondaryContact = 0 " +
            "LEFT JOIN sc.sponsor s")
    List<SponsorContactBasicDetails> findAllByOrderByLastName();

}