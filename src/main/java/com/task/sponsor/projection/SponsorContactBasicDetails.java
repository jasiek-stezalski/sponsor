package com.task.sponsor.projection;

import java.time.LocalDate;

public interface SponsorContactBasicDetails {
    Long getId();

    String getName();

    LocalDate getBeginDate();

    Boolean getPrimaryContact();

    Boolean getSecondaryContact();

    String getFirstName();

    String getLastName();

    String getInformalName();
}