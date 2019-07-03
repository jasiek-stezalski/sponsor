package com.task.sponsor.service;

import com.task.sponsor.converter.SponsorConverter;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import com.task.sponsor.dto.SponsorSummaryDto;
import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import com.task.sponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SponsorSummaryDto> findAll() {
        List<SponsorContactBasicDetails> basicDetails = repository.findAllByActiveTrueOrderByName();
        LinkedHashMap<Long, List<SponsorContactBasicDetails>> sponsorBasicDetailsMap = basicDetails.stream()
                .collect(Collectors
                        .groupingBy(SponsorContactBasicDetails::getId, LinkedHashMap::new, Collectors.toList()));

        return sponsorBasicDetailsMap.values().stream()
                .map(converter::convert)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deactivate(Long id) {
        Sponsor sponsor = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + id));
        sponsor.setActive(Boolean.FALSE);
        sponsor.getSponsorContacts().forEach(sponsorContact -> sponsorContact.setEndDate(LocalDateTime.now()));
        repository.save(sponsor);
    }

    @Transactional
    public SponsorDto update(Sponsor sponsor) {
        repository.findById(sponsor.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + sponsor.getId()));
        return converter.convert(repository.save(sponsor));
    }
}
