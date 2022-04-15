package com.montealegreluis.tickebeast.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@PropertySource({"stripe.properties"})
public class StripeConfiguration {
  @Value("${stripe.apiKey}")
  private String apiKey;
}
