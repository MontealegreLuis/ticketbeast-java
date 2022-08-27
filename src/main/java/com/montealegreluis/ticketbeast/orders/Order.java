package com.montealegreluis.ticketbeast.orders;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.ConfirmationNumberConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.EmailConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.LastFourDigitsConverter;
import com.montealegreluis.ticketbeast.concerts.CodesGenerator;
import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.concerts.Ticket;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
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
public final class Order extends AggregateRoot implements Response {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "order_id", length = 36))
  })
  private Uuid id;

  @Convert(converter = ConfirmationNumberConverter.class)
  private ConfirmationNumber confirmationNumber;

  @Convert(converter = EmailConverter.class)
  private Email email;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "order_amount")),
    @AttributeOverride(name = "currency", column = @Column(name = "order_currency"))
  })
  private Money total;

  @Convert(converter = LastFourDigitsConverter.class)
  private LastFourDigits cardLast4Digits;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Ticket> tickets;

  public static Order place(
      final Uuid orderId,
      final ConfirmationNumber confirmationNumber,
      final Email email,
      final Set<Ticket> tickets,
      final ProcessedCharge charge,
      final CodesGenerator generator) {
    final Order order = new Order(orderId, confirmationNumber, email, tickets, charge, generator);
    order.recordThat(
        new OrderHasBeenPlaced(
            order.id,
            confirmationNumber,
            email,
            tickets.stream().map(Ticket::id).collect(Collectors.toList())));
    return order;
  }

  private Order(
      final Uuid id,
      final ConfirmationNumber confirmationNumber,
      final Email email,
      final Set<Ticket> tickets,
      final ProcessedCharge charge,
      final CodesGenerator generator) {
    this.id = id;
    this.confirmationNumber = confirmationNumber;
    this.email = email;
    addTickets(tickets, generator);
    this.total = charge.amount();
    this.cardLast4Digits = charge.cardLast4Digits();
  }

  private void addTickets(final Set<Ticket> tickets, final CodesGenerator generator) {
    final Object[] orderedTickets = tickets.toArray();
    for (int i = 0; i < orderedTickets.length; i++) {
      ((Ticket) orderedTickets[i]).claimFor(this, i + 1L, generator);
    }
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
