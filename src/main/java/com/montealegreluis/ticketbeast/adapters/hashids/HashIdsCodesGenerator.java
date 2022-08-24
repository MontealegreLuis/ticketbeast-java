package com.montealegreluis.ticketbeast.adapters.hashids;

import com.montealegreluis.ticketbeast.concerts.Code;
import com.montealegreluis.ticketbeast.concerts.CodesGenerator;
import org.hashids.Hashids;

public class HashIdsCodesGenerator implements CodesGenerator {
  private final Hashids encoder;

  public HashIdsCodesGenerator(final String salt) {
    encoder = new Hashids(salt, 6, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
  }

  @Override
  public Code generateCodeFor(final Long number) {
    return new Code(encoder.encode(number));
  }
}
