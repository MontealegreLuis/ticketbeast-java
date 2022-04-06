package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.AdditionalInformationConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.SubtitleConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.TitleConverter;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.orders.*;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "concerts")
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Concert extends AggregateRoot implements Response {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "concert_id", length = 36))
  })
  private Uuid id;

  @Convert(converter = TitleConverter.class)
  private Title title;

  @Convert(converter = SubtitleConverter.class)
  private Subtitle subtitle;

  @Column(name = "concert_date")
  private Date date;

  @Embedded
  @AttributeOverrides({
    @AttributeOverride(name = "amount", column = @Column(name = "ticket_price_amount")),
    @AttributeOverride(name = "currency", column = @Column(name = "ticket_price_currency"))
  })
  private Money ticketPrice;

  @Embedded private Venue venue;

  @Convert(converter = AdditionalInformationConverter.class)
  private AdditionalInformation additionalInformation;

  private Date publishedAt;

  @OneToMany(mappedBy = "concert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Order> orders;

  @OneToMany(mappedBy = "concert", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<Ticket> tickets;

  public static Concert published(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation) {
    return new Concert(
        id,
        title,
        subtitle,
        date,
        ticketPrice,
        venue,
        additionalInformation,
        Date.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant()));
  }

  public static Concert draft(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation,
      TicketsQuantity ticketsQuantity) {
    final Concert concert =
        new Concert(id, title, subtitle, date, ticketPrice, venue, additionalInformation);
    concert.addTickets(ticketsQuantity);
    concert.recordThat(
        new ConcertWasDrafted(
            concert.id, concert.tickets.stream().map(Ticket::id).collect(Collectors.toList())));
    return concert;
  }

  private void addTickets(TicketsQuantity quantity) {
    tickets =
        IntStream.rangeClosed(1, quantity.value())
            .mapToObj(index -> Ticket.forConcert(Uuid.generate(), this))
            .collect(Collectors.toSet());
  }

  private Concert(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation,
      Date publishedAt) {
    this(id, title, subtitle, date, ticketPrice, venue, additionalInformation);
    this.publishedAt = publishedAt;
  }

  private Concert(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation) {
    this.id = id;
    this.title = title;
    this.subtitle = subtitle;
    this.date = date;
    this.ticketPrice = ticketPrice;
    this.venue = venue;
    this.additionalInformation = additionalInformation;
    this.orders = new HashSet<>();
  }

  public boolean isPublished() {
    return publishedAt != null;
  }

  @Override
  public Uuid id() {
    return id;
  }

  public boolean occursAfter(Date date) {
    return this.date.after(date);
  }

  public Date date() {
    return date;
  }

  public void publish() {
    this.publishedAt = Date.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
  }

  public Money priceForTickets(int quantity) {
    return ticketPrice.multiply(quantity);
  }

  public Order placeOrder(Uuid orderId, Set<Ticket> tickets, Email email, Money total) {
    Order order = Order.place(orderId, this, email, tickets, total);
    orders.add(order);
    recordThat(
        new OrderHasBeenPlaced(
            order.id(), email, id, tickets.stream().map(Ticket::id).collect(Collectors.toList())));
    return order;
  }

  public Set<Ticket> availableTickets(TicketsQuantity quantity) throws NotEnoughTickets {
    final List<Ticket> availableTickets =
        tickets.stream().filter(Ticket::isAvailable).collect(Collectors.toList());
    if (availableTickets.size() < quantity.value()) {
      throw NotEnoughTickets.available(availableTickets.size(), quantity.value());
    }
    return availableTickets.stream().skip(0).limit(quantity.value()).collect(Collectors.toSet());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || this.getClass() != o.getClass()) return false;
    Concert concert = (Concert) o;
    return id != null && Objects.equals(id, concert.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
