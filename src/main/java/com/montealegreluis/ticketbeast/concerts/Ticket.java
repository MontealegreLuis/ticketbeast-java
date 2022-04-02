package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.domainevents.Identifier;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Objects;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tickets")
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Ticket {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "ticket_id", length = 36))
  })
  private Uuid id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "concert_id", nullable = false)
  private Concert concert;

  static Ticket forConcert(Uuid id, Concert concert) {
    return new Ticket(id, concert);
  }

  private Ticket(Uuid id, Concert concert) {
    this.id = id;
    this.concert = concert;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public boolean isAvailable() {
    return order == null;
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

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
