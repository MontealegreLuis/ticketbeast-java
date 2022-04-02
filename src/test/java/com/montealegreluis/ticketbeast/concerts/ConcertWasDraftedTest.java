package com.montealegreluis.ticketbeast.concerts;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.tickebeast.builders.Value;
import java.util.List;
import org.junit.jupiter.api.Test;

final class ConcertWasDraftedTest {
  @Test
  void it_knows_its_name_and_its_aggregate_id() {
    var concertId = Value.id();
    var event = new ConcertWasDrafted(concertId, List.of(Value.id()));

    assertEquals(concertId.value(), event.aggregateId());
    assertEquals("concerts.concertWasDrafted", event.name());
  }
}
