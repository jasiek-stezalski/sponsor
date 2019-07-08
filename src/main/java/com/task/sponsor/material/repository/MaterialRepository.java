package com.task.sponsor.material.repository;

import com.task.sponsor.material.domain.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
