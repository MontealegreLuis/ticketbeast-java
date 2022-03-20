package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.EmailConverter;
import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Order {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "order_id", length = 36))
  })
  private Uuid id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "concert_id", nullable = false)
  private Concert concert;

  @Convert(converter = EmailConverter.class)
  private Email email;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Ticket> tickets;

  public static Order place(Uuid orderId, Concert concert, TicketsQuantity quantity, Email email) {
    final Order order = new Order(orderId, concert, email);
    final Set<Ticket> tickets =
        IntStream.rangeClosed(1, quantity.value())
            .mapToObj(index -> Ticket.forOrder(Uuid.generate(), order))
            .collect(Collectors.toSet());
    order.addTickets(tickets);
    return order;
  }

  private void addTickets(Set<Ticket> tickets) {
    this.tickets = tickets;
  }

  private Order(Uuid id, Concert concert, Email email) {
    this.id = id;
    this.concert = concert;
    this.email = email;
  }

  public int ticketsCount() {
    return tickets.size();
  }

  public Set<Ticket> getTickets() {
    return tickets;
  }

  public Uuid id() {
    return id;
  }

  public Money total() {
    return concert.priceForTickets(tickets.size());
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
