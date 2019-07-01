package com.task.sponsor.projection;

import java.time.LocalDate;
import java.util.List;

public interface ContactBasicDetails {
    String getFirstName();

    String getLastName();

    String getInformalName();

    List<SponsorContactsBasicDetails> getSponsorContacts();

    interface SponsorContactsBasicDetails {
        SponsorBasicDetails getSponsor();

        LocalDate getBeginDate();

        Boolean getPrimaryContact();

        Boolean getSecondaryContact();

        interface SponsorBasicDetails {
            String getName();
        }
    }
}