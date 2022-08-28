package com.montealegreluis.ticketbeast.concerts;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

import com.montealegreluis.servicebuses.domainevents.Identifier;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.CodeConverter;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "tickets")
@Access(FIELD)
@NoArgsConstructor(access = PROTECTED)
public final class Ticket {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "ticket_id", length = 36))
  })
  private Uuid id;

  @Convert(converter = CodeConverter.class)
  private Code code;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = EAGER, optional = false)
  @JoinColumn(name = "concert_id", nullable = false)
  private Concert concert;

  private Date reservedAt;

  static Ticket forConcert(Uuid id, Concert concert) {
    return new Ticket(id, concert);
  }

  private Ticket(Uuid id, Concert concert) {
    this.id = id;
    this.concert = concert;
  }

  public void claimFor(final Order order) {
    this.order = order;
  }

  public void assignCode(final Long ticketNumber, final CodesGenerator generator) {
    this.code = generator.generateCodeFor(ticketNumber);
  }

  public boolean isAvailable() {
    return order == null && reservedAt == null;
  }

  public Money price() {
    return concert.ticketPrice();
  }

  public Identifier id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    Ticket ticket = (Ticket) o;
    return id != null && Objects.equals(id, ticket.id);
  }

  public void reserve() {
    reservedAt = Date.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
