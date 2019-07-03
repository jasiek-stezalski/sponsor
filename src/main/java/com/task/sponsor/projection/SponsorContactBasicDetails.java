package com.task.sponsor.projection;

public interface SponsorContactBasicDetails {
    Long getId();

    String getName();

    Boolean getPrimaryContact();

    Boolean getSecondaryContact();

    String getFirstName();

    String getLastName();

    String getInformalName();
}