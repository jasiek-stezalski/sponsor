package com.task.sponsor.repository;

import com.task.sponsor.domain.SponsorContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SponsorContactRepository extends JpaRepository<SponsorContact, Long> {
}
