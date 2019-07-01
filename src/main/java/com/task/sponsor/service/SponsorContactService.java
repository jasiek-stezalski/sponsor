package com.task.sponsor.service;

import com.task.sponsor.common.SponsorContactId;
import com.task.sponsor.converter.ContactConverter;
import com.task.sponsor.converter.SponsorContactConverter;
import com.task.sponsor.converter.SponsorConverter;
import com.task.sponsor.domain.SponsorContact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.dto.SponsorContactDto;
import com.task.sponsor.dto.SponsorDto;
import com.task.sponsor.exception.DataConflictException;
import com.task.sponsor.repository.SponsorContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
public class SponsorContactService {

    private final SponsorContactRepository repository;
    private final SponsorService sponsorService;
    private final ContactService contactService;
    private final SponsorConverter sponsorConverter;
    private final ContactConverter contactConverter;
    private final SponsorContactConverter sponsorContactConverter;

    public SponsorContactService(SponsorContactRepository repository, SponsorService sponsorService, ContactService contactService,
                                 SponsorConverter sponsorConverter, ContactConverter contactConverter, SponsorContactConverter sponsorContactConverter) {
        this.repository = repository;
        this.sponsorService = sponsorService;
        this.contactService = contactService;
        this.sponsorConverter = sponsorConverter;
        this.contactConverter = contactConverter;
        this.sponsorContactConverter = sponsorContactConverter;
    }

    @Transactional
    public SponsorContactDto associateContact(Long sponsorId, Long contactId, Boolean primary, Boolean secondary) {
        if (primary == Boolean.TRUE && secondary == Boolean.TRUE) {
            throw new DataConflictException("Contact can not be primary and secondary at the same time");
        }
        SponsorDto sponsor = sponsorService.findById(sponsorId);
        ContactDto contact = contactService.findById(contactId);

        SponsorContact sponsorContact = SponsorContact.builder()
                .sponsor(sponsorConverter.convert(sponsor))
                .contact(contactConverter.convert(contact))
                .id(new SponsorContactId(sponsorId, contactId))
                .beginDate(LocalDate.now())
                .primaryContact(primary)
                .secondaryContact(secondary)
                .build();

        return sponsorContactConverter.convert(repository.save(sponsorContact));
    }
}
