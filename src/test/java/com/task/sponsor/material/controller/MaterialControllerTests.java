package com.task.sponsor.material.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.MaterialH2TestConfig;
import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.domain.Website;
import com.task.sponsor.material.dto.WebsiteDto;
import com.task.sponsor.material.repository.MaterialRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.task.sponsor.common.JsonConverter.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplication.class, MaterialH2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MaterialControllerTests {

    private File file;
    private Website website;
    private ConferenceCall conferenceCall;
    private WebsiteDto websiteDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MaterialRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        file = File.builder().fileUuid(1L).build();
        file = repository.save(file);

        website = Website.builder().url("url").build();
        website = repository.save(website);
        websiteDto = WebsiteDto.builder().url("url").id(website.getId()).build();

        conferenceCall = ConferenceCall.builder().code("code").build();
        conferenceCall = repository.save(conferenceCall);
    }

    @Test
    public void addSupportMaterial() throws Exception {
        this.mockMvc.perform(put("/material/support/{material_id}/{support_id}", file.getId(), website.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(websiteDto)));
    }

    @Test
    public void addSupportMaterial_ConferenceCallAsSupportMaterial() throws Exception {
        this.mockMvc.perform(put("/material/support/{material_id}/{support_id}", file.getId(), conferenceCall.getId()))
                .andDo(print())
                .andExpect(status().isConflict());
    }
}