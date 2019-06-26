package com.task.sponsor.repository;

import com.task.sponsor.domain.Contact;
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
        List<Contact> contacts = repository.findAllByOrderByLastName();

        assertEquals(3, contacts.size());
        assertEquals(contact1, contacts.get(0));
        assertEquals(contact2, contacts.get(1));
        assertEquals(contact3, contacts.get(2));
    }

    @Test
    public void findAllByOrderByLastName_emptyTable() {
        repository.deleteAll();

        List<Contact> contacts = repository.findAllByOrderByLastName();

        assertEquals(0, contacts.size());
    }
}