package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
import com.task.sponsor.projection.ContactBasicDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ContactRepositoryTests {

    private Contact contact1;
    private Contact contact2;
    private Contact contact3;

    @Autowired
    private ContactRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        contact1 = Contact.builder().firstName("contact1").lastName("contact1").cellNumber(1).email("contact1").build();
        contact2 = Contact.builder().firstName("contact2").lastName("contact2").cellNumber(1).email("contact2").build();
        contact3 = Contact.builder().firstName("contact3").lastName("contact3").cellNumber(1).email("contact3").build();

        repository.save(contact1);
        repository.save(contact2);
        repository.save(contact3);
    }

    @Test
    public void findAllByOrderByLastName() {
        List<ContactBasicDetails> contacts = repository.findAllByOrderByLastName();

        assertEquals(3, contacts.size());
        assertEquals(contact1.getLastName(), contacts.get(0).getLastName());
        assertEquals(contact1.getSponsorContacts(), contacts.get(0).getSponsorContacts());
        assertEquals(contact2.getLastName(), contacts.get(1).getLastName());
        assertEquals(contact2.getSponsorContacts(), contacts.get(1).getSponsorContacts());
        assertEquals(contact3.getLastName(), contacts.get(2).getLastName());
        assertEquals(contact3.getSponsorContacts(), contacts.get(2).getSponsorContacts());
    }

    @Test
    public void findAllByOrderByLastName_emptyTable() {
        repository.deleteAll();

        List<ContactBasicDetails> contacts = repository.findAllByOrderByLastName();

        assertEquals(0, contacts.size());
    }
}