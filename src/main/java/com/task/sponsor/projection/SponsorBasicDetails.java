package com.task.sponsor.projection;

import java.time.LocalDate;
import java.util.List;

public interface SponsorBasicDetails {
    String getName();

    List<SponsorContactsBasicDetails> getSponsorContacts();

    interface SponsorContactsBasicDetails {
        ContactBasicDetails getContact();

        LocalDate getBeginDate();

        Boolean getPrimaryContact();

        Boolean getSecondaryContact();

        interface ContactBasicDetails {
            String getFirstName();

            String getLastName();

            String getInformalName();
        }
    }
}