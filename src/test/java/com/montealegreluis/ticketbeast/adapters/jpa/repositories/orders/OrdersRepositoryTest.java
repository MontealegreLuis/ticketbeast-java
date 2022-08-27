package com.montealegreluis.ticketbeast.adapters.jpa.repositories.orders;

import com.montealegreluis.tickebeast.config.RepositoriesConfiguration;
import com.montealegreluis.tickebeast.contracttests.OrdersContractTest;
import com.montealegreluis.tickebeast.fixtures.OrdersFixture;
import com.montealegreluis.ticketbeast.adapters.jpa.repositories.concerts.ConcertsJpaRepository;
import com.montealegreluis.ticketbeast.adapters.jpa.repositories.concerts.ConcertsRepository;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.Orders;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@Tag("slow")
@ActiveProfiles("test")
@EntityScan("com.montealegreluis.ticketbeast")
@ContextConfiguration(classes = {RepositoriesConfiguration.class})
@SpringBootTest(
    classes = {
      ConcertsRepository.class,
      ConcertsJpaRepository.class,
      OrdersRepository.class,
      OrdersJpaRepository.class
    })
final class OrdersRepositoryTest extends OrdersContractTest {
  @Override
  public Orders orders() {
    return orders;
  }

  @Override
  public OrdersFixture scenario() {
    return new OrdersFixture(concerts);
  }

  @Autowired private OrdersRepository orders;
  @Autowired private Concerts concerts;
}
