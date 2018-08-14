# Spring Boot Kata
### Purpose

The purpose of this repository is to get a hands on to spring boot.

The microservice should provide credit information: Is a specific user allowed to use extra credit or not.

### Credit rules

* every unknown user is allowed to get 250 EUR of credit.
* every known user is allowed to get 20% of its already payed amount but 250 EUR at min and 3000 EUR at max.
* unconfirmed credit requests count as credit with a lifetime of 1 hour

### Todos
- [x] use spring boot initializer
- [x] add hello worldish controller
- [ ] add unit tests
- [ ] add integration tests
- [ ] add database with migrations
- [ ] add event sourcing
- [ ] add authentication and authorization

