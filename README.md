# TicketBeast

[![CI workflow](https://github.com/montealegreluis/ticketbeast-java/actions/workflows/ci.yml/badge.svg)](https://github.com/montealegreluis/ticketbeast-java/actions/workflows/ci.yml)


Port to Java of the application developed in [Test Driven Laravel](https://testdrivenlaravel.com).

## Tests

Use make to run the tests

```
$ make check
```

In order to run the test suite you'll need a [Stripe API Key](https://stripe.com/docs/keys).
Please add your key to the file `src/main/resources/stripe.properties` using the command below

```bash
echo "stripe.apiKey=YOUR_STRIPE_API_KEY" > src/testFixtures/resources/stripe.properties
```
