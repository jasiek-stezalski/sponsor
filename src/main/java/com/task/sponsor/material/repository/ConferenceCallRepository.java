package com.task.sponsor.material.repository;

import com.task.sponsor.material.domain.ConferenceCall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceCallRepository extends JpaRepository<ConferenceCall, Long> {
}
