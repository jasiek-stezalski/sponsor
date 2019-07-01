package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.projection.ContactBasicDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<ContactBasicDetails> findAllByOrderByLastName();

}