package com.task.sponsor.service;

import com.task.sponsor.converter.ContactConverter;
import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository repository;
    private final ContactConverter converter;

    public ContactService(ContactRepository repository, ContactConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    public ContactDto save(Contact contact) {
        return converter.convert(repository.save(contact));
    }

    // todo the same
    public ContactDto update(Contact contact) {
        return converter.convert(repository.save(contact));
    }

    // todo the same
    public ContactDto findById(Long id) {
        return repository.findById(id).map(ContactDto::new).get();
    }

    public List<ContactDto> findAll() {
        return repository.findAllByOrderByLastName().stream().map(ContactDto::new).collect(Collectors.toList());
    }
}
