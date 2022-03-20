package com.montealegreluis.ticketbeast.adapters.jpa.repostories.concerts;

import com.montealegreluis.tickebeast.config.RepositoriesConfiguration;
import com.montealegreluis.tickebeast.contracttests.ConcertsContractTest;
import com.montealegreluis.ticketbeast.concerts.Concerts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ActiveProfiles("test")
@EntityScan("com.montealegreluis.ticketbeast")
@ContextConfiguration(classes = {RepositoriesConfiguration.class})
@SpringBootTest(classes = {ConcertsRepository.class, JpaConcerts.class})
final class ConcertsRepositoryTest extends ConcertsContractTest {
  @Autowired ConcertsRepository concerts;

  @Override
  protected Concerts concerts() {
    return concerts;
  }
}
