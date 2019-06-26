package com.task.sponsor;

import com.task.sponsor.config.H2TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SponsorApplication.class, H2TestConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SponsorApplicationTests {

    @Test
    public void contextLoads() {
    }

}
