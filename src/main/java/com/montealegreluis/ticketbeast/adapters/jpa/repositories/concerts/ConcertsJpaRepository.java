package com.montealegreluis.ticketbeast.adapters.jpa.repositories.concerts;

import com.montealegreluis.ticketbeast.concerts.Concert;
import com.montealegreluis.ticketbeast.shared.Uuid;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConcertsJpaRepository extends JpaRepository<Concert, Uuid> {
  Optional<Concert> findByIdAndDateAfterAndPublishedAtNotNull(Uuid concertId, Date currentDate);
}
