package com.montealegreluis.tickebeast.builders.adapters.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;
import java.util.HashMap;
import java.util.Map;

public final class TokenBuilder {
  private RequestOptions requestOptions;
  private int creditCardYear;
  private String creditCardNumber = "4242424242424242";

  public static TokenBuilder aToken() {
    return new TokenBuilder();
  }

  public TokenBuilder withRequestOptions(RequestOptions requestOptions) {
    this.requestOptions = requestOptions;
    return this;
  }

  public TokenBuilder withCreditCardYear(int creditCardYear) {
    this.creditCardYear = creditCardYear;
    return this;
  }

  public TokenBuilder withCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
    return this;
  }

  public Token build() throws StripeException {
    var card = new HashMap<>();
    card.put("number", creditCardNumber);
    card.put("exp_month", 1);
    card.put("exp_year", creditCardYear);
    card.put("cvc", "123");
    Map<String, Object> params = new HashMap<>();
    params.put("card", card);

    return Token.create(params, requestOptions);
  }
}
