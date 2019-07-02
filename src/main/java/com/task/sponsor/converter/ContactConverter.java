package com.task.sponsor.converter;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.dto.ContactSummaryDto;
import com.task.sponsor.dto.SponsorContactDto;
import com.task.sponsor.dto.SponsorContactSummaryDto;
import com.task.sponsor.projection.SponsorContactBasicDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
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

    public ContactSummaryDto convert(List<SponsorContactBasicDetails> contactDetailsList) {
        if (CollectionUtils.isEmpty(contactDetailsList)) {
            return null;
        }

        return ContactSummaryDto.builder()
                .id(contactDetailsList.get(0).getId())
                .firstName(contactDetailsList.get(0).getFirstName())
                .lastName(contactDetailsList.get(0).getLastName())
                .informalName(contactDetailsList.get(0).getInformalName())
                .sponsorContacts(contactDetailsList.stream().map(cd -> new SponsorContactSummaryDto(cd, true, false)).collect(Collectors.toList()))
                .build();
    }
}