package com.task.sponsor.repository;

import com.task.sponsor.domain.Sponsor;
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
public class SponsorRepositoryTests {

    private Sponsor sponsor1;
    private Sponsor sponsor2;
    private Sponsor sponsor3;

    @Autowired
    private SponsorRepository repository;

    @Before
    public void before() {
        repository.deleteAll();

        sponsor1 = Sponsor.builder().name("sponsor1").active(Boolean.TRUE).build();
        sponsor2 = Sponsor.builder().name("sponsor2").active(Boolean.TRUE).build();
        sponsor3 = Sponsor.builder().name("sponsor3").active(Boolean.FALSE).build();

        repository.save(sponsor1);
        repository.save(sponsor2);
        repository.save(sponsor3);
    }

    @Test
    public void findAllByActiveTrueOrderByName() {
        List<Sponsor> sponsors = repository.findAllByActiveTrueOrderByName();

        assertEquals(2, sponsors.size());
        assertEquals(sponsor1, sponsors.get(0));
        assertEquals(sponsor2, sponsors.get(1));
    }

    @Test
    public void findAllByActiveTrueOrderByName_emptyTable() {
        repository.deleteAll();

        List<Sponsor> sponsors = repository.findAllByActiveTrueOrderByName();

        assertEquals(0, sponsors.size());
    }
}