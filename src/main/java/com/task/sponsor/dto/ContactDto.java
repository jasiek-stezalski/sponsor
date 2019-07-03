package com.task.sponsor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.task.sponsor.domain.Address;
import com.task.sponsor.domain.Certificate;
import com.task.sponsor.domain.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ContactDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String informalName;
    private Integer cellNumber;
    private String email;
    private LocalDate birthday;
    private String jobTitle;
    private Address address;
    private Set<Certificate> certificates;
    private List<SponsorContactDto> sponsorContacts;

    public ContactDto(Contact contact, boolean isSponsorContact) {
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.informalName = contact.getInformalName();
        this.cellNumber = contact.getCellNumber();
        this.email = contact.getEmail();
        this.birthday = contact.getBirthday();
        this.jobTitle = contact.getJobTitle();
        this.address = contact.getAddress();
        this.certificates = contact.getCertificates();
        this.address = contact.getAddress();
        if (isSponsorContact && !CollectionUtils.isEmpty(contact.getSponsorContacts())) {
            this.sponsorContacts = contact.getSponsorContacts().stream().map(sp -> new SponsorContactDto(sp, true, false)).collect(Collectors.toList());
        }
    }
}
