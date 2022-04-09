package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.EmailConverter;
import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.concerts.Reservation;
import com.montealegreluis.ticketbeast.concerts.Ticket;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Order extends AggregateRoot {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "order_id", length = 36))
  })
  private Uuid id;

  @Convert(converter = EmailConverter.class)
  private Email email;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "order_amount")),
    @AttributeOverride(name = "currency", column = @Column(name = "order_currency"))
  })
  private Money total;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Ticket> tickets;

  public static Order place(Uuid orderId, Email email, Reservation reservation) {
    final Order order = new Order(orderId, email, reservation);
    order.recordThat(
        new OrderHasBeenPlaced(
            order.id,
            email,
            reservation.tickets().stream().map(Ticket::id).collect(Collectors.toList())));
    return order;
  }

  private Order(Uuid id, Email email, Reservation reservation) {
    this.id = id;
    this.email = email;
    addTickets(reservation.tickets());
    this.total = reservation.cost();
  }

  private void addTickets(Set<Ticket> tickets) {
    tickets.forEach(ticket -> ticket.setOrder(this));
    this.tickets = tickets;
  }

  public Uuid id() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    Order order = (Order) o;
    return id != null && Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
