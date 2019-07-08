package com.task.sponsor.material.repository;

import com.task.sponsor.material.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
