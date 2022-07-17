package com.montealegreluis.ticketbeast.orders;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;

final class ConfirmationNumberTest {
  @Test
  void it_cannot_have_a_blank_value() {
    assertThrows(IllegalArgumentException.class, () -> new ConfirmationNumber("   "));
    assertThrows(IllegalArgumentException.class, () -> new ConfirmationNumber(""));
    assertThrows(IllegalArgumentException.class, () -> new ConfirmationNumber(null));
  }

  @Test
  void it_generates_confirmation_numbers_24_characters_long() {
    var confirmationNumber = ConfirmationNumber.generate();

    assertEquals(24, confirmationNumber.value().length());
  }

  @Test
  void it_generates_confirmation_numbers_with_uppercase_letters_and_numbers_only() {
    var confirmationNumber = ConfirmationNumber.generate();

    assertTrue(Pattern.matches("^[A-Z0-9]+$", confirmationNumber.value()));
  }

  @Test
  void it_generates_confirmation_numbers_without_ambiguous_characters() {
    var confirmationNumber = ConfirmationNumber.generate();

    assertFalse(confirmationNumber.value().contains("I"));
    assertFalse(confirmationNumber.value().contains("1"));
    assertFalse(confirmationNumber.value().contains("O"));
    assertFalse(confirmationNumber.value().contains("0"));
  }

  @Test
  void it_generates_unique_confirmation_numbers() {
    var confirmationNumbers =
        IntStream.rangeClosed(1, 100)
            .mapToObj(i -> ConfirmationNumber.generate())
            .collect(Collectors.toSet());

    assertEquals(100, confirmationNumbers.size());
  }
}
