package com.montealegreluis.ticketbeast.adapters.jpa.repostories.concerts;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.values.Uuid;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaConcerts extends JpaRepository<Concert, Uuid> {
  Optional<Concert> findByIdAndDateAfterAndPublishedAtNotNull(Uuid concertId, Date now);
}
