package com.task.sponsor.material.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.MaterialH2TestConfig;
import com.task.sponsor.material.domain.ConferenceCall;
import com.task.sponsor.material.dto.ConferenceCallDto;
import com.task.sponsor.material.repository.ConferenceCallRepository;
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
public class ConferenceCallControllerTests {

    private ConferenceCall conferenceCall;
    private ConferenceCallDto conferenceCallDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ConferenceCallRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        conferenceCall = ConferenceCall.builder().code("code").build();
        conferenceCall = repository.save(conferenceCall);

        conferenceCallDto = ConferenceCallDto.builder().code("code").id(conferenceCall.getId()).build();
    }

    @Test
    public void createFile() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/material/conference_call")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(conferenceCall)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(conferenceCallDto)));
    }


    @Test
    public void updateConferenceCall() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/conference_call")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(conferenceCall)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(conferenceCallDto)));
    }

    @Test
    public void updateConferenceCall_wrongId() throws Exception {
        conferenceCall.setId(-1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/conference_call")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(conferenceCall)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getConferenceCallById() throws Exception {
        this.mockMvc.perform(get("/material/conference_call/{id}", conferenceCall.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(conferenceCallDto)));
    }

    @Test
    public void getConferenceCallById_wrongId() throws Exception {
        this.mockMvc.perform(get("/material/conference_call/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllConferenceCalls_emptyTable() throws Exception {
        repository.deleteAll();

        this.mockMvc.perform(get("/material/conference_call"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.EMPTY_LIST)));
    }

}
