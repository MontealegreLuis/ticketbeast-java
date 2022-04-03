package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.domainevents.DomainEvent;
import com.montealegreluis.servicebuses.domainevents.Identifier;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public final class ConcertWasDrafted extends DomainEvent {
  private final String concertId;
  private final List<String> ticketIds;

  public ConcertWasDrafted(Uuid concertId, List<Identifier> ticketIds) {
    this.concertId = concertId.value();
    this.ticketIds = ticketIds.stream().map(Object::toString).collect(Collectors.toList());
  }

  @Override
  public String name() {
    return "concerts.concertWasDrafted";
  }

  @Override
  public String aggregateId() {
    return concertId;
  }
}
