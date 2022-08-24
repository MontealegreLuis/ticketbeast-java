package com.montealegreluis.ticketbeast.adapters.hashids;

import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

final class HashIdsCodesGeneratorTest {
  @Test
  void it_generates_codes_that_are_6_characters_long() {
    var generator = new HashIdsCodesGenerator("a salt");

    var code = generator.generateCodeFor(1L);

    assertEquals(6, code.value().length());
  }

  @Test
  void it_generates_codes_that_only_contain_uppercase_letters() {
    var generator = new HashIdsCodesGenerator("a salt");

    var code = generator.generateCodeFor(1L);

    assertTrue(
        Pattern.matches("^[A-Z]*$", code.value()),
        code.value() + " should contain only uppercase letters");
  }

  @Test
  void it_generates_the_same_code_for_the_same_number() {
    var generator = new HashIdsCodesGenerator("a salt");

    var codeA = generator.generateCodeFor(1L);
    var codeB = generator.generateCodeFor(1L);

    assertEquals(codeA, codeB);
  }

  @Test
  void it_generates_different_codes_for_different_numbers() {
    var generator = new HashIdsCodesGenerator("a salt");

    var codeA = generator.generateCodeFor(1L);
    var codeB = generator.generateCodeFor(2L);

    assertNotEquals(codeA, codeB);
  }

  @Test
  void it_generates_different_codes_for_the_same_values_when_salts_are_different() {
    var generatorA = new HashIdsCodesGenerator("a salt");
    var generatorB = new HashIdsCodesGenerator("another salt");

    var codeA = generatorA.generateCodeFor(1L);
    var codeB = generatorB.generateCodeFor(1L);

    assertNotEquals(codeA, codeB);
  }
}
