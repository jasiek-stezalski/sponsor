package com.task.sponsor.service;

import com.task.sponsor.converter.SponsorConverter;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
import com.task.sponsor.repository.SponsorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    //todo add Exception for optional
    public SponsorDto findById(Long id) {
        return repository.findById(id).map(SponsorDto::new).get();
    }

    public List<SponsorDto> findAll() {
        return repository.findAllByActiveTrueOrderByName().stream().map(SponsorDto::new).collect(Collectors.toList());
    }

    // todo check if exist
    public void deactivate(Long id) {
        Sponsor byId = repository.findById(id).get();
        byId.setActive(false);
        repository.save(byId);
    }

    // todo check if exist
    @Transactional
    public SponsorDto update(Sponsor sponsor) {
        return converter.convert(repository.save(sponsor));
    }
}
