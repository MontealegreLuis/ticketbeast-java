package com.montealegreluis.ticketbeast.concerts.address;

import static com.montealegreluis.tickebeast.builders.concerts.address.AddressBuilder.anAddress;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

final class AddressTest {
  @Test
  void it_can_be_compared_to_another_address() {
    var addressA = anAddress().withZipCode("1234").build();
    var addressB = anAddress().withZipCode("5678").build();

    assertEquals(addressA, addressA);
    assertNotEquals(addressA, addressB);
    assertNotEquals(addressA, null);
    assertNotEquals(addressB, null);
  }
}
