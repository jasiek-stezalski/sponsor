package com.task.sponsor.converter;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import org.springframework.stereotype.Component;

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
        return ContactDto.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .informalName(contact.getInformalName())
                .cellNumber(contact.getCellNumber())
                .email(contact.getEmail())
                .birthday(contact.getBirthday())
                .jobTitle(contact.getJobTitle())
                .build();
    }
}