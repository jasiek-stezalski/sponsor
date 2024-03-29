package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.domain.SponsorContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorContactRepository extends JpaRepository<SponsorContact, Long> {

    SponsorContact findByContactAndSponsorAndEndDateIsNull(Contact contact, Sponsor sponsor);

}
