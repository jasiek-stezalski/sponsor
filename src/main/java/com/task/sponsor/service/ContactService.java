package com.task.sponsor.service;

import com.task.sponsor.converter.ContactConverter;
import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.exception.ResourceNotFoundException;
import com.task.sponsor.projection.ContactBasicDetails;
import com.task.sponsor.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ContactDto update(Contact contact) {
        repository.findById(contact.getId())
                .orElseThrow(() -> new ResourceNotFoundException("There is no Sponsor with id: " + contact.getId()));
        return converter.convert(repository.save(contact));
    }

    public ContactDto findById(Long id) {
        return converter.convert(repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("There is no Contact with id: " + id)));
    }

    public List<ContactBasicDetails> findAll() {
        return repository.findAllByOrderByLastName();
    }
}
