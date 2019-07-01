package com.task.sponsor.repository;

import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.projection.SponsorBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    List<SponsorBasicDetails> findAllByActiveTrueOrderByName();

}
