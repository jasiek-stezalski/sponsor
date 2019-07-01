package com.task.sponsor.converter;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.dto.SponsorContactDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.stream.Collectors;

@Component
public class ContactConverter {

    public Contact convert(ContactDto contactDto) {
        return Contact.builder()
                .id(contactDto.getId())
                .firstName(contactDto.getFirstName())
                .lastName(contactDto.getLastName())
                .informalName(contactDto.getInformalName())
                .cellNumber(contactDto.getCellNumber())
                .email(contactDto.getEmail())
                .birthday(contactDto.getBirthday())
                .jobTitle(contactDto.getJobTitle())
                .build();
    }

    public ContactDto convert(Contact contact) {
        ContactDto contactDto = ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .informalName(contact.getInformalName())
                .cellNumber(contact.getCellNumber())
                .email(contact.getEmail())
                .birthday(contact.getBirthday())
                .jobTitle(contact.getJobTitle())
                .build();

        if (contact.getAddress() != null) {
            contactDto.setAddress(contact.getAddress());
        }

        if (!CollectionUtils.isEmpty(contact.getCertificates())) {
            contactDto.setCertificates(contact.getCertificates());
        }

        if (!CollectionUtils.isEmpty(contact.getSponsorContacts())) {
            contactDto.setSponsorContacts(contact.getSponsorContacts().stream()
                    .map(sp -> new SponsorContactDto(sp, true, false))
                    .collect(Collectors.toList()));
        }
        return contactDto;
    }
}