package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.domainevents.AggregateRoot;
import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.AdditionalInformationConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.SubtitleConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.TitleConverter;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.orders.Email;
import com.montealegreluis.ticketbeast.orders.Order;
import com.montealegreluis.ticketbeast.orders.OrderHasBeenPlaced;
import com.montealegreluis.ticketbeast.orders.TicketsQuantity;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
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

  public static Concert unpublished(
      Uuid id,
      Title title,
      Subtitle subtitle,
      Date date,
      Money ticketPrice,
      Venue venue,
      AdditionalInformation additionalInformation) {
    return new Concert(id, title, subtitle, date, ticketPrice, venue, additionalInformation);
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

  public Money priceForTickets(int quantity) {
    return ticketPrice.multiply(quantity);
  }

  public Order orderTickets(Uuid orderId, TicketsQuantity quantity, Email email) {
    Order order = Order.place(orderId, this, quantity, email);
    orders.add(order);
    // order.ticketsIds(); // ?
    recordThat(new OrderHasBeenPlaced(order.id(), email, id, order.ticketsCount()));
    return order;
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
