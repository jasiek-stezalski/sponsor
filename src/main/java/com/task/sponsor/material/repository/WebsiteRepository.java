package com.task.sponsor.material.repository;

import com.task.sponsor.material.domain.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
}