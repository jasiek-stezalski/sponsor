package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    // todo active?
    List<Contact> findAllByOrderByLastName();
}