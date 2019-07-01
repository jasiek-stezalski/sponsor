package com.task.sponsor.service;

import com.task.sponsor.converter.SponsorConverter;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.projection.SponsorBasicDetails;
import com.task.sponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class SponsorService {

    private final SponsorRepository repository;
    private final SponsorConverter converter;

    public SponsorService(SponsorRepository repository, SponsorConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public SponsorDto save(Sponsor sponsor) {
        return converter.convert(repository.save(sponsor));
    }

    public SponsorDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + id)));
    }

    public List<SponsorBasicDetails> findAll() {
        return repository.findAllByActiveTrueOrderByName();
    }

    @Transactional
    public void deactivate(Long id) {
        Sponsor sponsor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + id));
        sponsor.setActive(Boolean.FALSE);
        sponsor.getSponsorContacts().forEach(sponsorContact -> sponsorContact.setEndDate(LocalDate.now()));
        repository.save(sponsor);
    }

    @Transactional
    public SponsorDto update(Sponsor sponsor) {
        repository.findById(sponsor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + sponsor.getId()));
        return converter.convert(repository.save(sponsor));
    }
}
