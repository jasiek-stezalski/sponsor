package com.task.sponsor.material.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.MaterialH2TestConfig;
import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.WebsiteDto;
import com.task.sponsor.material.repository.WebsiteRepository;
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
@SpringBootTest(classes = {SponsorApplication.class, MaterialH2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class WebsiteControllerTests {

    private Website website;
    private WebsiteDto websiteDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebsiteRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        website = Website.builder().url("url").build();
        website = repository.save(website);

        websiteDto = WebsiteDto.builder().url("url").id(website.getId()).build();
    }

    @Test
    public void createWebsite() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/material/website")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(website)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(websiteDto)));
    }


    @Test
    public void updateWebsite() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/website")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(website)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(websiteDto)));
    }

    @Test
    public void updateWebsite_wrongId() throws Exception {
        website.setId(-1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/website")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(website)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getWebsiteById() throws Exception {
        this.mockMvc.perform(get("/material/website/{id}", website.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(websiteDto)));
    }

    @Test
    public void getWebsiteById_wrongId() throws Exception {
        this.mockMvc.perform(get("/material/website/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllWebsites_emptyTable() throws Exception {
        repository.deleteAll();

        this.mockMvc.perform(get("/material/website"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.EMPTY_LIST)));
    }

}
