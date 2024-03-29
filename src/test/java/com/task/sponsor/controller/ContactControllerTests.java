package com.task.sponsor.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.SponsorH2TestConfig;
import com.task.sponsor.domain.Contact;
import com.task.sponsor.dto.ContactDto;
import com.task.sponsor.repository.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static com.task.sponsor.common.JsonConverter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplication.class, SponsorH2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ContactControllerTests {

    private Contact contact1;
    private ContactDto contactDto1;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        contact1 = Contact.builder().firstName("contact1").lastName("contact1").informalName("contact1").cellNumber(1).email("contact1").build();
        contact1 = repository.save(contact1);

        contactDto1 = ContactDto.builder().id(contact1.getId()).firstName("contact1").lastName("contact1").informalName("contact1").cellNumber(1).email("contact1").build();
    }

    @Test
    public void createContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contact1)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(contactDto1)));
    }

    @Test
    public void updateContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contact1)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(contactDto1)));
    }

    @Test
    public void updateContact_wrongId() throws Exception {
        contact1.setId(-1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/contact")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(contact1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getContactById() throws Exception {
        this.mockMvc.perform(get("/contact/{id}", contact1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(contactDto1)));
    }

    @Test
    public void getContactById_wrongId() throws Exception {
        this.mockMvc.perform(get("/contact/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }


    @Test
    public void getAllContacts_emptyTable() throws Exception {
        repository.deleteAll();

        this.mockMvc.perform(get("/contact"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.EMPTY_LIST)));
    }
}