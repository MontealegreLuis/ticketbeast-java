package com.montealegreluis.ticketbeast.adapters.jpa.repositories.orders;

import static com.montealegreluis.tickebeast.builders.concerts.ConcertBuilder.aConcert;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.montealegreluis.tickebeast.builders.Value;
import com.montealegreluis.tickebeast.config.RepositoriesConfiguration;
import com.montealegreluis.ticketbeast.adapters.jpa.repositories.concerts.ConcertsJpaRepository;
import com.montealegreluis.ticketbeast.adapters.jpa.repositories.concerts.ConcertsRepository;
import com.montealegreluis.ticketbeast.concerts.*;
import com.montealegreluis.ticketbeast.orders.Orders;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
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
final class OrdersRepositoryTest {
  @Test
  void it_saves_an_order() throws NotEnoughTickets {
    var quantity = new TicketsQuantity(2);
    var concert = aConcert().withTicketsCount(quantity.value()).build();
    concerts.save(concert);
    var orderId = Value.id();
    var reservation = concert.reserveTickets(quantity, Value.email());
    var confirmationNumber = Value.confirmationNumber();
    var order = reservation.complete(orderId, confirmationNumber);

    orders.save(order);

    var savedOrder = ordersRepository.findById(orderId);
    assertTrue(savedOrder.isPresent());
    assertEquals(order, savedOrder.get());
  }

  @Autowired private Orders orders;
  @Autowired private Concerts concerts;
  @Autowired private OrdersJpaRepository ordersRepository;
}
