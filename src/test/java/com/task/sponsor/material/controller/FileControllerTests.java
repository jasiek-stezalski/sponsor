package com.task.sponsor.material.controller;

import com.task.sponsor.SponsorApplication;
import com.task.sponsor.config.MaterialH2TestConfig;
import com.task.sponsor.material.domain.File;
import com.task.sponsor.material.dto.FileDto;
import com.task.sponsor.material.repository.FileRepository;
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
public class FileControllerTests {

    private File file;
    private FileDto fileDto;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FileRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        file = File.builder().fileUuid(1L).build();
        file = repository.save(file);

        fileDto = FileDto.builder().fileUuid(1L).id(file.getId()).build();
    }

    @Test
    public void createFile() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/material/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(file)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fileDto)));
    }


    @Test
    public void updateFile() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(file)))
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fileDto)));
    }

    @Test
    public void updateFile_wrongId() throws Exception {
        file.setId(-1L);

        this.mockMvc.perform(MockMvcRequestBuilders.put("/material/file")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(file)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getFileById() throws Exception {
        this.mockMvc.perform(get("/material/file/{id}", file.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(fileDto)));
    }

    @Test
    public void getFileById_wrongId() throws Exception {
        this.mockMvc.perform(get("/material/file/{id}", -1L))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void getAllFiles_emptyTable() throws Exception {
        repository.deleteAll();

        this.mockMvc.perform(get("/material/file"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(asJsonString(Collections.EMPTY_LIST)));
    }

}
