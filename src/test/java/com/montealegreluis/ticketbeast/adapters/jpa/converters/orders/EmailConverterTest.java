package com.montealegreluis.ticketbeast.adapters.jpa.converters.orders;

import static org.junit.jupiter.api.Assertions.*;

import com.montealegreluis.ticketbeast.orders.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

final class EmailConverterTest {
  @Test
  void it_converts_email_to_database_value() {
    var emailAddress = "example@example.com";
    var email = new Email(emailAddress);

    assertEquals(emailAddress, converter.convertToDatabaseColumn(email));
    assertNull(converter.convertToDatabaseColumn(null));
  }

  @Test
  void it_converts_database_value_to_email() {
    var emailAddress = "example@example.com";
    var email = new Email(emailAddress);

    assertEquals(email, converter.convertToEntityAttribute(emailAddress));
    assertNull(converter.convertToEntityAttribute(null));
  }

  @BeforeEach
  void let() {
    converter = new EmailConverter();
  }

  private EmailConverter converter;
}
