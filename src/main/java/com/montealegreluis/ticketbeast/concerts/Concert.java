package com.montealegreluis.ticketbeast.concerts;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.AdditionalInformationConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.SubtitleConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.TitleConverter;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.orders.Email;
import com.montealegreluis.ticketbeast.orders.Reservation;
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

  @OneToMany(mappedBy = "concert", fetch = LAZY, cascade = ALL)
  private Set<Ticket> tickets;

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
        new Concert(
            id, title, subtitle, date, ticketPrice, venue, additionalInformation, ticketsQuantity);
    concert.recordThat(
        new ConcertWasDrafted(
            concert.id, concert.tickets.stream().map(Ticket::id).collect(Collectors.toList())));
    return concert;
  }

  private Concert(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation,
      TicketsQuantity ticketsQuantity) {
    this.id = id;
    this.title = title;
    this.subtitle = subtitle;
    this.date = date;
    this.ticketPrice = ticketPrice;
    this.venue = venue;
    this.additionalInformation = additionalInformation;
    addTickets(ticketsQuantity);
  }

  private void addTickets(TicketsQuantity quantity) {
    tickets =
        IntStream.rangeClosed(1, quantity.value())
            .mapToObj(index -> Ticket.forConcert(Uuid.generate(), this))
            .collect(Collectors.toSet());
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

  public Money ticketPrice() {
    return ticketPrice;
  }

  public void publish() {
    this.publishedAt = Date.from(OffsetDateTime.now(ZoneOffset.UTC).toInstant());
  }

  public Money priceForTickets(int quantity) {
    return ticketPrice.multiply(quantity);
  }

  public Reservation reserveTickets(
      final TicketsQuantity quantity, final Email email, final CodesGenerator generator)
      throws NotEnoughTickets {
    final List<Ticket> availableTickets =
        tickets.stream().filter(Ticket::isAvailable).collect(Collectors.toList());

    if (availableTickets.size() < quantity.value()) {
      throw NotEnoughTickets.available(availableTickets.size(), quantity.value());
    }

    final Set<Ticket> reservedTickets =
        availableTickets.stream().skip(0).limit(quantity.value()).collect(Collectors.toSet());

    final int offset = tickets.size() - availableTickets.size();
    final Object[] claimedTickets = reservedTickets.toArray();
    for (int i = 0; i < reservedTickets.size(); i++) {
      ((Ticket) claimedTickets[i]).assignCode(offset + 1L + i, generator);
    }

    return new Reservation(reservedTickets, email);
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
