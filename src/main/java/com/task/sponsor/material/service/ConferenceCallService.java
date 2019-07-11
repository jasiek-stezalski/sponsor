package com.task.sponsor.material.service;

import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.material.converter.ConferenceCallConverter;
import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.dto.ConferenceCallDto;
import com.task.sponsor.material.repository.ConferenceCallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConferenceCallService {

    private final ConferenceCallRepository repository;
    private final ConferenceCallConverter converter;

    public ConferenceCallService(ConferenceCallRepository repository, ConferenceCallConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public ConferenceCallDto save(ConferenceCall conferenceCall) {
        return converter.convert(repository.save(conferenceCall));
    }

    public ConferenceCallDto update(ConferenceCall conferenceCall) {
        repository.findById(conferenceCall.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Conference call with id: " + conferenceCall.getId()));
        return converter.convert(repository.save(conferenceCall));
    }

    public ConferenceCallDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Conference call with id: " + id)));
    }

    public List<ConferenceCallDto> findAll() {
        return repository.findAll().stream().map(converter::convert).collect(Collectors.toList());
    }
}
