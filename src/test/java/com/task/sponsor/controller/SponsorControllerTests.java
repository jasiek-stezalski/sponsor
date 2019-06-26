package com.task.sponsor.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.H2TestConfig;
import com.task.sponsor.domain.Sponsor;
import com.task.sponsor.dto.SponsorDto;
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

import java.util.Arrays;

import static com.task.sponsor.common.JsonConverter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplication.class, H2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SponsorControllerTests {

    private Sponsor sponsor1;
    private Sponsor sponsor2;
    private Sponsor sponsor3;
    private SponsorDto sponsorDto1;
    private SponsorDto sponsorDto2;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SponsorRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        sponsor1 = Sponsor.builder().name("sponsor1").active(Boolean.TRUE).build();
        sponsor2 = Sponsor.builder().name("sponsor2").active(Boolean.TRUE).build();
        sponsor3 = Sponsor.builder().name("sponsor3").active(Boolean.FALSE).build();

        sponsor1 = repository.save(sponsor1);
        sponsor2 = repository.save(sponsor2);
        sponsor3 = repository.save(sponsor3);

        sponsorDto1 = SponsorDto.builder().id(sponsor1.getId()).name("sponsor1").active(Boolean.TRUE).build();
        sponsorDto2 = SponsorDto.builder().id(sponsor2.getId()).name("sponsor2").active(Boolean.TRUE).build();
    }

    @Test
    public void createSponsor() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/sponsor")
                .contentType(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sponsor1)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(sponsorDto1)));
    }

    @Test
    public void updateSponsor() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/sponsor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sponsor1)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(sponsorDto1)));
    }

    @Test
    public void updateSponsor_wrongId() throws Exception {
        sponsor1.setId(-1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/sponsor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(sponsor1)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getSponsorById() throws Exception {
        this.mockMvc.perform(get("/sponsor/{id}", sponsor1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(sponsorDto1)));
    }

    @Test
    public void getSponsorById_wrongId() throws Exception {
        this.mockMvc.perform(get("/sponsor/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllSponsors() throws Exception {
        sponsorDto1 = SponsorDto.builder().id(sponsor1.getId()).name("sponsor1").build();
        sponsorDto2 = SponsorDto.builder().id(sponsor2.getId()).name("sponsor2").build();

        this.mockMvc.perform(get("/sponsor"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Arrays.asList(sponsorDto1, sponsorDto2))));
    }

    @Test
    public void deactivateSponsor() throws Exception {
        sponsorDto1 = SponsorDto.builder().id(sponsor1.getId()).name("sponsor1").active(Boolean.FALSE).build();

        this.mockMvc.perform(delete("/sponsor/{id}", sponsor1.getId()))
                .andDo(print())
                .andExpect(status().isOk());

        this.mockMvc.perform(get("/sponsor/{id}", sponsor1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(sponsorDto1)));
    }

    @Test
    public void deactivateSponsor_wrongId() throws Exception {
        this.mockMvc.perform(delete("/sponsor/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}