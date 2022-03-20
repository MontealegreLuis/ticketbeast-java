package com.montealegreluis.ticketbeast.orders;

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

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  static Ticket forOrder(Uuid id, Order order) {
    return new Ticket(id, order);
  }

  private Ticket(Uuid id, Order order) {
    this.id = id;
    this.order = order;
  }

  public Order getOrder() {
    return order;
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
