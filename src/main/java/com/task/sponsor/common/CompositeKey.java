package com.task.sponsor.common;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;

import java.io.Serializable;

public class CompositeKey implements Serializable {
    private Sponsor sponsor;
    private Contact contact;
}


