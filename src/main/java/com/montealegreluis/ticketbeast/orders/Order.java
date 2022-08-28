package com.montealegreluis.ticketbeast.orders;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static lombok.AccessLevel.PROTECTED;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.ConfirmationNumberConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.EmailConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.orders.LastFourDigitsConverter;
import com.montealegreluis.ticketbeast.concerts.Money;
import com.montealegreluis.ticketbeast.concerts.Ticket;
import com.montealegreluis.ticketbeast.payments.LastFourDigits;
import com.montealegreluis.ticketbeast.payments.ProcessedCharge;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "orders")
@Access(FIELD)
@NoArgsConstructor(access = PROTECTED)
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

  @OneToMany(mappedBy = "order", fetch = EAGER, cascade = ALL)
  private Set<Ticket> tickets;

  public static Order place(
      final Uuid orderId,
      final ConfirmationNumber confirmationNumber,
      final Email email,
      final Set<Ticket> tickets,
      final ProcessedCharge charge) {
    final Order order = new Order(orderId, confirmationNumber, email, tickets, charge);
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
      final ProcessedCharge charge) {
    this.id = id;
    this.confirmationNumber = confirmationNumber;
    this.email = email;
    addTickets(tickets);
    this.total = charge.amount();
    this.cardLast4Digits = charge.cardLast4Digits();
  }

  private void addTickets(final Set<Ticket> tickets) {
    tickets.forEach(orderedTicket -> orderedTicket.claimFor(this));
    this.tickets = tickets;
  }

  public Uuid id() {
    return id;
  }

  public ConfirmationNumber confirmationNumber() {
    return confirmationNumber;
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
