package com.task.sponsor.service;

import com.task.sponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;

@Service
public class SponsorService {

    private final SponsorRepository repository;

    public SponsorService(SponsorRepository repository) {
        this.repository = repository;
    }
}
