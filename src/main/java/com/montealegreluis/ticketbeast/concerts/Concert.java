package com.montealegreluis.ticketbeast.concerts;

import com.montealegreluis.servicebuses.querybus.Response;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.AdditionalInformationConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.SubtitleConverter;
import com.montealegreluis.ticketbeast.adapters.jpa.converters.concerts.TitleConverter;
import com.montealegreluis.ticketbeast.concerts.venue.Venue;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Access(AccessType.FIELD)
@Table(name = "concerts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class Concert implements Response {
  @Id
  @EmbeddedId
  @AttributeOverrides({
    @AttributeOverride(name = "identifier", column = @Column(name = "concert_id"))
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
        Date.from(Instant.now()));
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
  }

  public boolean isPublished() {
    return publishedAt != null;
  }

  public Uuid id() {
    return id;
  }

  public boolean occursAfter(Date date) {
    return this.date.after(date);
  }

  public Date date() {
    return date;
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
