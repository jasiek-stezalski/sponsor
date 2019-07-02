package com.task.sponsor.repository;

import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("SELECT s.id AS id, s.name AS name, sc.beginDate AS beginDate, sc.primaryContact AS primaryContact, " +
            "sc.secondaryContact AS secondaryContact, c.firstName AS firstName, c.lastName AS lastName, c.informalName AS informalName " +
            "FROM Sponsor s " +
            "LEFT JOIN s.sponsorContacts sc ON sc.endDate IS NULL AND sc.primaryContact = 1 AND sc.secondaryContact = 0 " +
            "LEFT JOIN sc.contact c")
    List<SponsorContactBasicDetails> findAllByActiveTrueOrderByName();

}