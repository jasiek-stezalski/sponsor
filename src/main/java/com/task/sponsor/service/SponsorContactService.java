package com.task.sponsor.service;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.domain.SponsorContact;
import com.task.sponsor.repository.SponsorContactRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SponsorContactService {

    private final SponsorContactRepository repository;
    private final SponsorService sponsorService;
    private final ContactService contactService;

    public SponsorContactService(SponsorContactRepository repository, SponsorService sponsorService, ContactService contactService) {
        this.repository = repository;
        this.sponsorService = sponsorService;
        this.contactService = contactService;
    }

//    // todo add date
//    @Transactional
//    public SponsorContact associateContact(Long sponsorId, Long contactId) {
//        Sponsor sponsor = sponsorService.findById(sponsorId);
//        Contact contact = contactService.findById(contactId);
//        SponsorContact build = SponsorContact.builder()
//                .sponsor(sponsor)
//                .contact(contact)
//                .build();
//
//        List<SponsorContact> sponsorContacts = sponsor.getSponsorContacts();
//        sponsorContacts.add(build);
//
//        sponsor.setSponsorContacts(sponsorContacts);
//        sponsorService.save(sponsor);
//
////        contact.setSponsorContacts(sponsorContacts);
////        contactService.save(contact);
//
//        return build;
////        return repository.save(build);
//    }
}
