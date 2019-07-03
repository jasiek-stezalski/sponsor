package com.task.sponsor.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.H2TestConfig;
import com.task.sponsor.domain.Contact;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.repository.ContactRepository;
import com.task.sponsor.repository.SponsorRepository;
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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplication.class, H2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SponsorContactControllerTests {

    private Sponsor sponsor;
    private Contact contact;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Before
    public void before() {
        sponsorRepository.deleteAll();
        sponsor = Sponsor.builder().name("sponsor").active(Boolean.TRUE).build();
        sponsor = sponsorRepository.save(sponsor);

        contactRepository.deleteAll();
        contact = Contact.builder().id(1L).firstName("contact").lastName("contact").cellNumber(2).email("contact").build();
        contact = contactRepository.save(contact);
    }

    @Test
    public void associateContact() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/assoc/{sponsor_id}/{contact_id}", sponsor.getId(), contact.getId())
                .param("primary", "true")
                .param("secondary", "false")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"sponsor\":{\"id\":" + sponsor.getId() + ",\"name\":\"sponsor\",\"active\":true},\"contact\":{\"id\":" + contact.getId() + ",\"firstName\":\"" +
                        "contact\",\"lastName\":\"contact\",\"cellNumber\":2,\"email\":\"contact\"},\"primaryContact\":true,\"secondaryContact\":false}"));
    }

    @Test
    public void associateContact_primaryAndSecondaryTrue() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/assoc/{sponsor_id}/{contact_id}", sponsor.getId(), contact.getId())
                .param("primary", "true")
                .param("secondary", "true")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}
